import os
import argparse
import gradio as gr
from gradio.themes.utils import colors

from tab_entity_extraction import build_entity_extraction_tab
from tab_event_extraction import build_event_extraction_tab

# if __name__ == "__main__":


# 设置临时目录路径
os.environ['GRADIO_TEMP_DIR'] = '/mnt/nas/mm/ie_env/tmp'
parser = argparse.ArgumentParser()
parser.add_argument("--private", default=False, action='store_true')
parser.add_argument("--port", default=7860, type=int)
parser.add_argument("--controller_host", default="127.0.0.1", type=str)
args = parser.parse_args()

theme=gr.themes.Soft(primary_hue=colors.gray, neutral_hue=colors.neutral)
js_func = """
function refresh() {
    const url = new URL(window.location);

    if (url.searchParams.get('__theme') !== 'dark') {
        url.searchParams.set('__theme', 'dark');
        window.location.href = url.href;
    }
}
"""
with gr.Blocks(theme=theme,js=js_func) as demo:
    gr.Markdown("""荣耀智慧大模型demo平台""")
    with gr.Tabs(elem_classes="tab-buttons") as tabs:
        with gr.TabItem("实体提取", elem_id="chat", id=1):
            build_entity_extraction_tab()

        with gr.TabItem("事件提取", elem_id="chat", id=2):
            build_event_extraction_tab()
        
        # with gr.TabItem("屏幕理解", elem_id="info", id=3):
        #     build_screen_comprehension_tab()

        # with gr.TabItem("抽象语义提取", elem_id="info", id=4):
        #     build_abstract_semantic_extraction_tab()

        # with gr.TabItem("指令图像编辑", elem_id="info", id=5):
        #     build_picture_edit_tab()

        # with gr.TabItem("About Us", elem_id="info", id=0):
        #     build_about_us_tab()

if args.private:
    demo.queue().launch(share=False, server_name="127.0.0.1", server_port=args.port, max_threads=1)
else:
    demo.queue().launch(share=True, server_name="0.0.0.0", server_port=args.port, max_threads=1)





--------

import datetime
import logging
import logging.handlers
import os
import sys

import requests

from constants import LOGDIR

server_error_msg = "**NETWORK ERROR DUE TO HIGH TRAFFIC. PLEASE REGENERATE OR REFRESH THIS PAGE.**"
moderation_msg = "YOUR INPUT VIOLATES OUR CONTENT MODERATION GUIDELINES. PLEASE TRY AGAIN."

handler = None


def build_logger(logger_name, logger_filename):
    global handler

    formatter = logging.Formatter(
        fmt="%(asctime)s | %(levelname)s | %(name)s | %(message)s",
        datefmt="%Y-%m-%d %H:%M:%S",
    )

    # Set the format of root handlers
    if not logging.getLogger().handlers:
        logging.basicConfig(level=logging.INFO)
    logging.getLogger().handlers[0].setFormatter(formatter)

    # Redirect stdout and stderr to loggers
    stdout_logger = logging.getLogger("stdout")
    stdout_logger.setLevel(logging.INFO)
    sl = StreamToLogger(stdout_logger, logging.INFO)
    sys.stdout = sl

    stderr_logger = logging.getLogger("stderr")
    stderr_logger.setLevel(logging.ERROR)
    sl = StreamToLogger(stderr_logger, logging.ERROR)
    sys.stderr = sl

    # Get logger
    logger = logging.getLogger(logger_name)
    logger.setLevel(logging.INFO)

    # Add a file handler for all loggers
    if handler is None:
        os.makedirs(LOGDIR, exist_ok=True)
        filename = os.path.join(LOGDIR, logger_filename)
        handler = logging.handlers.TimedRotatingFileHandler(
            filename, when='D', utc=True)
        handler.setFormatter(formatter)

        for name, item in logging.root.manager.loggerDict.items():
            if isinstance(item, logging.Logger):
                item.addHandler(handler)

    return logger


class StreamToLogger(object):
    """
    Fake file-like stream object that redirects writes to a logger instance.
    """
    def __init__(self, logger, log_level=logging.INFO):
        self.terminal = sys.stdout
        self.logger = logger
        self.log_level = log_level
        self.linebuf = ''

    def __getattr__(self, attr):
        return getattr(self.terminal, attr)

    def write(self, buf):
        temp_linebuf = self.linebuf + buf
        self.linebuf = ''
        for line in temp_linebuf.splitlines(True):
            # From the io.TextIOWrapper docs:
            #   On output, if newline is None, any '\n' characters written
            #   are translated to the system default line separator.
            # By default sys.stdout.write() expects '\n' newlines and then
            # translates them so this is still cross platform.
            if line[-1] == '\n':
                self.logger.log(self.log_level, line.rstrip())
            else:
                self.linebuf += line

    def flush(self):
        if self.linebuf != '':
            self.logger.log(self.log_level, self.linebuf.rstrip())
        self.linebuf = ''


def disable_torch_init():
    """
    Disable the redundant torch default initialization to accelerate model creation.
    """
    import torch
    setattr(torch.nn.Linear, "reset_parameters", lambda self: None)
    setattr(torch.nn.LayerNorm, "reset_parameters", lambda self: None)


def violates_moderation(text):
    """
    Check whether the text violates OpenAI moderation API.
    """
    url = "https://api.openai.com/v1/moderations"
    headers = {"Content-Type": "application/json",
               "Authorization": "Bearer " + os.environ["OPENAI_API_KEY"]}
    text = text.replace("\n", "")
    data = "{" + '"input": ' + f'"{text}"' + "}"
    data = data.encode("utf-8")
    try:
        ret = requests.post(url, headers=headers, data=data, timeout=5)
        flagged = ret.json()["results"][0]["flagged"]
    except requests.exceptions.RequestException as e:
        flagged = False
    except KeyError as e:
        flagged = False

    return flagged


def pretty_print_semaphore(semaphore):
    if semaphore is None:
        return "None"
    return f"Semaphore(value={semaphore._value}, locked={semaphore.locked()})"
--------

"""
model adapter
"""
import torch
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
        return tokenizer, chat_model

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

