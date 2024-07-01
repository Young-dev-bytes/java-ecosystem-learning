"""
model adapter
"""
import torch
import sys
sys.path.insert(0, '/home/jovyan/hyjprojects/InternVL')
# from src_internvl.modeling_internvl_chat import InternVLChatModel
# from src_internvl.tokenization_internlm2 import InternLM2Tokenizer
# from internvl_utils import load_image
from transformers import AutoModelForCausalLM, AutoTokenizer

torch.manual_seed(1234)


gen_kwargs = {"max_length": 2048, "num_beams": 1, "do_sample": False, "top_p": 0.7,
                "top_k": 50, "temperature": 0.95, "repetition_penalty": 1.04}

class InternVLModelAdapter:
    def __init__(self, model_path, device):
        self.model_path = model_path
        self.device = device
        self.tokenizer, self.chat_model = self.load_models()

    def load_models(self):
        # load model
        chat_model = AutoModelForCausalLM.from_pretrained(
            self.model_path,
            trust_remote_code=True,
        )

        chat_model = chat_model.to(self.device)
        chat_model.config.use_cache = False
        chat_model = chat_model.eval()


        # load tokenizer 
        tokenizer = AutoTokenizer.from_pretrained(
            self.model_path,
            model_max_length=1024,
            padding_side="right",
            use_fast=False,
            add_eos_token=False,
            add_bos_token=False,
            trust_remote_code=True,
        )
        # set special marks
        tokenizer.pad_token = '<|extra_1|>'
        tokenizer.eos_token = '<|endoftext|>'
        tokenizer.unk_token = '<unk>'

        # tokenizer = InternLM2Tokenizer.from_pretrained(
        #     self.model_path, trust_remote_code=True)
        
        # chat_model = InternVLChatModel.from_pretrained(
        #     self.model_path,
        #     torch_dtype=torch.bfloat16,
        #     low_cpu_mem_usage=True,
        #     device_map=self.device,
        #     trust_remote_code=True).eval()

        return tokenizer, chat_model

    def predict(self, text, image, max_tiles=14, num_beams=1, max_new_tokens=512, do_sample=False):
        pixel_values = load_image(image, max_num=max_tiles).to(torch.bfloat16)\
            .cuda(self.device)
        
        generation_config = {
            "num_beams": num_beams,
            "max_new_tokens": max_new_tokens,
            "do_sample": do_sample,
        }

        response, history = self.chat_model.chat(
            self.tokenizer, pixel_values, text, generation_config, history=None, return_history=True)

        return response

    def llm_predict1(self, samples):
        all_samples=[]
        all_result = []
        for x in samples:
            x["type"] = ""
            x["dsl"] = ""
            x["数据源"] = ""
            x["描述"] = ""
            x["具体描述"] = ""
            all_samples.append(x)
            prompt_sample = self.prompt_fun(all_samples)[0][0]
            print("-------------- prompt --------------")
            print(prompt_sample)
            input_data = prompt_sample['input']
            input_data  = input_data.replace("<TOKENS_UNUSED_1>", "<|im_start|>").replace("<TOKENS_UNUSED_2>", "<|im_end|>")
            print("-------------- input_data --------------")
            print(input_data)
            inputs = self.tokenizer(input_data, return_tensors="pt", padding="longest")
            inputs = self.to_device(inputs, self.device)
            results = self.chat_model.generate(inputs['input_ids'], attention_mask=inputs['attention_mask'], max_new_tokens=1024, **gen_kwargs)
            res_ids = results.tolist()[0]
            output_content = self.tokenizer.decode(res_ids[len(inputs["input_ids"][0]):-1])
            all_samples[-1]['dsl']=output_content
            all_samples[-1]['assistant']=x["assistant"]
            # all_result=all_result+"/"+output_content
            all_result.append(output_content)
            # print(all_samples)
        return all_result    

    def prompt_fun(self, item):
        """对于json对话进行prompt拼接"""
        train_list = []
        try:
            if len(item) == 1:
                prompt = "<TOKENS_UNUSED_1>你是一个能够理解对话过程，并从中抽取对话意图和对应槽位的助手。" \
                            "我定义了两个字段：'用户输入'和'历史状态', 其中'用户输入'表示用户的输入话术；" \
                            "'历史状态'表示你基于'用户输入'字段抽取出来的意图和槽位结果，并按照固定格式组合。其中'历史状态'字段格式要符合以下要求: \n" \
                            "1. 句法格式为: 意图名称(槽位类型1=槽位值1, 槽位类型2=槽位值2, 槽位类型3=槽位值3, ...)；\n" \
                            "2. 句法格式中的意图名称是根据'用户输入'理解生成的具体意图名。" \
                            "槽位类型1、槽位类型2、槽位类型3等，是该意图可能存在的槽位类型，槽位值1、槽位值2、槽位值3等是从原文中抽取出来的符合该槽位类型的具体值，并且该值一定是在'用户输入'字段原文中，如果某个槽位类型没有具体值，则槽位值用None字符表示；\n" \
                            "3. 槽位类型不仅仅只有3个，最少可能是0个，也可能是多于3个；\n" \
                            "4. 多个意图用 AND 进行拼接" \
                            "5. 其中提醒相关的意图，如果涉及条件关系用system.if来包含条件关系, THEN表示条件之后执行的任务现在我将给你用户当前的'用户输入', " \
                            "那么基于最新的'用户输入'，你要帮我抽取生成最新的'历史状态'。\n"
                prompt = prompt + '用户输入: ' + item[0]['user'] + '\n历史状态: ' + '\n\n<TOKENS_UNUSED_2>\n'
                train_list.append({'input': prompt, 'output': item[0]['dsl']})
        except Exception as e:
            print(e.__str__())
            print(item)
        return train_list, None

    def to_device(self, batch: dict, device) -> dict:
        output = {}
        for k, v in batch.items():
            try:
                output[k] = v.to(device)
            except:
                output[k] = v
        return output    
