
import os
import argparse
import gradio as gr
from gradio.themes.utils import colors

import requests

num_chatbots = 3


def get_args():
    parser = argparse.ArgumentParser()
    parser.add_argument("--private", default=False, action="store_true")
    parser.add_argument("--port", default=11300, type=int)
    parser.add_argument("--controller_host", default="127.0.0.1", type=str)
    args = parser.parse_args()
    return args

def build_control_panel():
    with gr.Accordion("parameters", open = True, visible=True) as parameter_row:
        chat_max_output_tokens = gr.Slider(
            minimum=0,
            maximum=1024,
            value=512,
            step=64,
            interactive=True,
            label="Max output tokens",
        )
        chat_num_beams = gr.Slider(
            minimum=1,
            maximum=5,
            value=1,
            step=1,
            interactive=True,
            label="Beam Size",
        )
        chat_repetition_penalty = gr.Slider(
            minimum=1,
            maximum=5,
            value=1,
            step=1,
            interactive=True,
            label="Beam Size",
        )
        chat_do_sample = gr.Checkbox(interactive=True, label="Do_example")
    return chat_max_output_tokens, chat_num_beams,chat_repetition_penalty, chat_do_sample



def build_model_selector():
    tab = "实体提取",
    model_names = ["None"]
    pload = {
        "tab": tab
    }
    args = get_args()
    # model_names += requests.post(f"http://{args.controller_host}:21001/list_models", json = pload).json()['model_names']
    model_selector = gr.Dropdown(
        choices=["自研Agent模型1","自研Agent模型2","自研Agent模型"],
        value=model_names[-1],
        interactive=True,
        show_label=True,
        container=False,
        scale=5
    )
    return model_selector

def build_single_chatbot(chatbot_id):
    chatbot_id = str(chatbot_id)
    with gr.Group(elem_id = "share-region-named" + chatbot_id):
        with gr.Row(elem_id = "model_selector_row" + chatbot_id):
            model_selector = build_model_selector()
            model_selector.change()
            b = gr.Button("Refresh", scale = 1)
            b.click(build_model_selector, [], model_selector)

        with gr.Accordion("结构化数据", open = True, visible = True) as json_row:    
            json_viewers = gr.Dataframe()

        with gr.Accordion("对话历史", open = True, visible = True) as chatbot_row:
            chatbot = gr.Chatbot(
                elem_id="chatbot" + chatbot_id,
                label = "Model" + chatbot_id,
                height = 600
            )     

    return model_selector, chatbot, json_viewers



def build_entity_extraction_tab():

    with gr.Row():
        with gr.Column(scale=3):
            imagebox = gr.Image(
                type='filepath', 
                interactive=True,
                sources=["upload", "clipboard"]
            )
            with gr.Accordion("level 1", open=False ,visible=True):
                gr.Examples(
                    examples=[
                        ['examples/entity_extraction/level_1_event_1.jpg'],
                        ['examples/entity_extraction/level_1_event_3.jpg'],
                        ['examples/entity_extraction/level_1_post_2.jpg'],
                    ],
                inputs=[imagebox],
                outputs=[imagebox],
                cache_examples=False)

            with gr.Accordion("level 2", open=False ,visible=True):
                gr.Examples(
                    examples=[
                        ['examples/entity_extraction/level_2_event_1.jpg'],
                        ['examples/entity_extraction/level_2_post_1.jpg'],
                        ['examples/entity_extraction/level_2_post_2.jpg'],
                    ],
                inputs=[imagebox],
                outputs=[imagebox],
                cache_examples=False)
    
            with gr.Accordion("level 3", open=False ,visible=True):
                gr.Examples(
                    examples=[
                        ['examples/entity_extraction/level_3_post_3.jpg'],
                        ['examples/entity_extraction/level_3_post_1.jpg'],
                        ['examples/entity_extraction/level_3_post_2.jpg'],
                    ],
                inputs=[imagebox],
                outputs=[imagebox],
                cache_examples=False)
            chat_max_output_tokens, chat_num_beams, \
            chat_repetition_penalty, chat_do_sample = build_control_panel()

        with gr.Column(scale=9):
            chat_states = [gr.State([]) for _ in range(num_chatbots)]
            model_selectors = [None] * num_chatbots
            chatbots = [None] * num_chatbots
            json_viewers = [None] * num_chatbots

            with gr.Row():    
                # for i in range(num_chatbots):
                #     with gr.Column(scale=3):
                #         chat_states[i] = gr.State()
                #         model_selectors[i], chatbots[i], json_viewers[i] = build_single_chatbot(i)
                    with gr.Column(scale=3):
                        chat_states[i] = gr.State()
                        model_selectors[i], chatbots[i], json_viewers[i] = build_single_chatbot(i)

            with gr.Row():
                with gr.Column(scale=8):
                    chat_textbox = gr.Textbox(
                        value="请从当前输入的图片中进行实体提取，注意不同title的图片关注返回的实体类型不一样，请注意区分。不得返回每种title图片关注之外的实体。结果以List存储一个或多个map的方式进行返回。",
                        lines=5,
                        show_label=False,
                        visible=True)

                with gr.Column(scale=2, min_width=100):
                    submit_btn = gr.Button(value="Submit", variant="primary",
                                        visible=True)
                    regenerate_btn = gr.Button(value="🔄 Regenerate",
                                            interactive=True)
                    
                    clear_btn = gr.Button(value="🗑️  Clear history",
                                        interactive=False)
        def change_textbox(text):
            prompts = [
                    "请从当前输入的图片中进行实体提取，注意不同title的图片关注返回的实体类型不一样，请注意区分。不得返回每种title图片关注之外的实体。结果以List存储一个或多个map的方式进行返回。",
                    "请从当前输入的图片中提取作品名，时间，地址3种实体并存储到字典中，针对没有出现的实体，请用''进行值的替换。每种实体如涉及多个实体值，实体值用列表罗列，结果以json形式输出",
                    # "请从当前输入的图片中提取实体，结果以List存储一个或多个map的方式进行返回。"
                ]
            for i in prompts:
                if i != text:
                    return gr.Textbox(
                        value=i,
                        lines=5,
                        show_label=False,
                        visible=True)

        regenerate_btn.click(change_textbox, chat_textbox, chat_textbox)
        parameter_list = [
                chat_max_output_tokens, chat_repetition_penalty,
                chat_num_beams, chat_do_sample
            ]

        submit_btn.click(
            add_text,
            [chat_textbox] + model_selectors,
            chatbots + [chat_textbox]
        ).then(
            predict, 
            model_selectors + chatbots + [imagebox],
            chatbots + json_viewers)

    with gr.Row():
        gr.Markdown(
        """
            ### Model Author: 杨  赫(00031743)
            ### Demo Author: 黄逸嘉(00031952)、张晟辉(00032043)
        """)




------------------------------------------------------------------------------------------------------------------------------

import argparse
import gradio as gr
from gradio.themes.utils import colors
import time
# import torch
import pandas as pd
import numpy as np
from PIL import Image,ImageDraw,ImageFont
# import torch.nn.functional as F

# from model_configs import MODELS
import requests
from gradio_modal import Modal

num_chatbots = 2



def build_entity_extraction_tab():


    with gr.Row():
        with gr.Column(scale=3):
            inputText = gr.Textbox(visible=False, show_copy_button=True)
            with gr.Accordion("case 1", open=True ,visible=True):
                examples = gr.Examples(examples=['123','456'],
                inputs=[inputText],
                outputs=[inputText],
                cache_examples=False)

        with gr.Column(scale=9):
            chat_states = [gr.State([]) for _ in range(num_chatbots)]
            model_selectors = [None] * num_chatbots
            chatbots = [None] * num_chatbots
            json_viewers = [None] * num_chatbots

            def change_textbox_(text):
                return text

            with gr.Row():
                for i in range(num_chatbots):
                    with gr.Column(scale=3):
                        chat_states[i] = gr.State()
                        model_selectors[i], chatbots[i], json_viewers[i] = build_single_chatbot(i)            

            with gr.Row():

                with gr.Column(scale=8):
                    submit = gr.Button(value='Convert to grayscale') 
                    chat_textbox = gr.Textbox(
                        lines=2,
                        show_label=False,
                        visible=True)

                    # submit.click(fn=change_textbox_, inputs=inputText, outputs=chat_textbox)   
                    inputText.change(fn=change_textbox_, inputs=inputText, outputs=chat_textbox)   


                with gr.Column(scale=2, min_width=100):
                    submit_btn = gr.Button(value="Submit", variant="primary", visible=True)
                    clear_btn = gr.Button(value="🗑️  Clear history", interactive=True)
                    load_btn = gr.Button(value="Load Model", interactive=True)
                with Modal(visible=False) as modal:
                    gr.Markdown("<center>将会加载下列模型，预计需要几分钟，是否确认加载</center>")    
                    gr.Markdown("<center>qwen-7b, qwen-8b</center>")    
                    
                    with gr.Group():
                        with gr.Row():
                            btn = gr.Button("YES", variant="primary")
                            btn2 = gr.Button("NO", variant="secondary")


        
         

        md = gr.Markdown()
        submit_btn.click(
            add_text,
            [chat_textbox] + model_selectors,
            chatbots + [chat_textbox],
            show_progress = False
        ).then(
            predict, 
            model_selectors + chatbots + [inputText],
            chatbots + json_viewers)   
        load_btn.click(lambda: Modal(visible=True), None, modal)    
        # load_btn.click(fn=load_model, inputs=gr.Text(),outputs=gr.Text())  
        btn2.click(lambda: Modal(visible=False), None, modal)       



def predict(model_selector1, model_selectors2, 
            chat_history1, chat_history2, image):
    model_selectors = [model_selector1, model_selectors2]
    chat_history_list = [chat_history1, chat_history2]
    json_list = [None, None]
    print(image)
    
    for i in range(num_chatbots):
        model_selected = model_selectors[i]
        if model_selected == "None":
            chat_history = [[None, None]]
        else:
            time.sleep(20)
            text = chat_history_list[i][0][0]
            response = predict_single_http(model_selected, text, image)
                    
            chat_history = [[text, response]]
            chat_history_list[i] = chat_history

            print(response)
            try:
                json_list[i] = text_to_dataframe(response)
            except:
                json_list[i] = pd.DataFrame()

        print(chat_history_list)
        print(json_list)
    return chat_history_list + json_list + ["??????"]

def build_single_chatbot(chatbot_id): 
    chatbot_id = str(chatbot_id)
    gr.Markdown("# <h3>🕵️‍♂️ 自研Agent模型</h3>")
    with gr.Group(elem_id="share-region-named"+chatbot_id):
        with gr.Row(elem_id="model_selector_row"+chatbot_id):
            model_selector = build_model_selector()
            model_selector.change()
            print(model_selector.value)
            load_b = gr.Button("加载模型", scale=1, elem_id="model_selector_row"+chatbot_id)
            # load_b.click(build_model_selector, [], model_selector)
            # load_b.click(fn=load_model, inputs=gr.Text(), outputs=gr.Text())
            load_b.click(fn=load_model, inputs=[model_selector, gr.State("加载模型")], outputs=load_b)
        json_viewer = gr.Dataframe(visible=False)

        with gr.Accordion("对话历史", open=True, visible=True) as chatbot_row:
            chatbot = gr.Chatbot(
                elem_id="chatbot"+chatbot_id,
                elem_classes=["chatbot"+chatbot_id, "hide"],
                label="Model "+chatbot_id,
                height=600,
            )
    return model_selector, chatbot, json_viewer   

def load_model(model_name, button_label):
    if button_label == "加载模型":
        # Logic to load the model
        print(f"Loading model: {model_name}")
        return "卸载模型"
    else:
        # Logic to unload the model
        print(f"Unloading model: {model_name}")
        return "加载模型"

def build_model_selector():
    print("click")
    tab = '单意图任务'
    model_names = ["None"]
    pload = {
        "tab": tab
    }
    # model_names += requests.post(f"http://{args.controller_host}:21001/list_models",json=pload).json()['model_names']
    # model_names += requests.post(f"http://localhost:21001/list_models",json=pload).json()['model_names']
    model_names = ["qwen-7b", "qwen-8b", "qwen-9b","None"]

    model_selector = gr.Dropdown(
                choices= model_names,
                value=model_names[-1],
                interactive=True,
                show_label=False,
                container=False,
                scale=5
            )
    return model_selector

def add_text(text, model_selector1, model_selector2):
    chat_history_list = [None, None]
    model_selectors = [model_selector1, model_selector2]
    for i in range(num_chatbots):
        if model_selectors[i] != "None":
            chat_history = [[text, None]]
            chat_history_list[i] = chat_history
    return chat_history_list + [""] 

def predict_single_http(model_name, text, image):
    # try:
        # Stream output
    # args = get_args()
    # print(args.port)
    # print("---------" + args.controller_host)
    controller_address = 'http://localhost:21001/get_worker_address'
    headers = {"User-Agent": "Demo Client"}
    pload = {
        "model": model_name,
        'text': text,
        'image': image

    }
    print(pload)
    # model server addr
    # worker_addr = requests.post(controller_address, json=pload).json()['address']
    # print(worker_addr)
    # try:
    #     # resp
    #     response = requests.post(worker_addr + "/worker_generate",
    #         headers=headers, json=pload, stream=False, timeout=10).json()
    # except:
    #     response = "似乎卡住了，再试一下呢"
    response = ["123"]
    print(response)
    time.sleep(2)
    return response               




