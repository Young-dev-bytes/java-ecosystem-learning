import argparse
import gradio as gr
from gradio.themes.utils import colors
import torch
import pandas as pd
import numpy as np
from PIL import Image,ImageDraw,ImageFont
import torch.nn.functional as F

# from model_configs import MODELS
import requests

num_chatbots = 1



def build_entity_extraction_tab():

    with gr.Row():
        with gr.Column(scale=3):
            imagebox = gr.Text(visible=False)
            with gr.Accordion("case 1", open=False ,visible=True):
                gr.Examples(
                    examples=[
                        # ['examples/entity_extraction/level_1_event_1.jpg'],
                        # ['examples/entity_extraction/level_1_event_3.jpg'],
                        # ['examples/entity_extraction/level_1_post_2.jpg'],
                    ],
                inputs=[imagebox],
                outputs=[imagebox],
                cache_examples=False)

            with gr.Accordion("case 2", open=False ,visible=True):
                gr.Examples(
                    examples=[
                        # ['examples/entity_extraction/level_2_event_1.jpg'],
                        # ['examples/entity_extraction/level_2_post_1.jpg'],
                        # ['examples/entity_extraction/level_2_post_2.jpg'],
                    ],
                inputs=[imagebox],
                outputs=[imagebox],
                cache_examples=False)
    
            with gr.Accordion("case 3", open=False ,visible=True):
                gr.Examples(
                    examples=[
                        # ['examples/entity_extraction/level_3_post_3.jpg'],
                        # ['examples/entity_extraction/level_3_post_1.jpg'],
                        # ['examples/entity_extraction/level_3_post_2.jpg'],
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
                  
                for i in range(num_chatbots):
                    with gr.Column(scale=3):
                        chat_states[i] = gr.State()
                        model_selectors[i], chatbots[i], json_viewers[i] = build_single_chatbot(i)

                for i in range(num_chatbots):
                    with gr.Column(scale=3):
                        chat_states[i] = gr.State()
                        model_selectors[i], chatbots[i], json_viewers[i] = build_single_chatbot2(i) 

                for i in range(num_chatbots):
                    with gr.Column(scale=3):
                        chat_states[i] = gr.State()
                        model_selectors[i], chatbots[i], json_viewers[i] = build_single_chatbot3(i)                 

            with gr.Row():
                with gr.Column(scale=8):
                    chat_textbox = gr.Textbox(
                        value="打开闹钟",
                        lines=5,
                        show_label=False,
                        visible=True)

                with gr.Column(scale=2, min_width=100):
                    submit_btn = gr.Button(value="Submit", variant="primary",
                                        visible=True)
                    regenerate_btn = gr.Button(value="🔄 Regenerate",
                                            interactive=True)
                    
                    clear_btn = gr.Button(value="🗑️  Clear history",
                                        interactive=True)




        def change_textbox(text):
            prompts = [
                    "打开闹钟",
                    "今天天气怎么样",
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

        # clear_btn.click()    

        submit_btn.click(
            add_text,
            [chat_textbox] + model_selectors,
            chatbots + [chat_textbox]
        ).then(
            predict, 
            model_selectors + chatbots + [imagebox],
            chatbots + json_viewers)          



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
    
def build_control_panel():
    with gr.Accordion("Parameters", open=False,
                                      visible=True) as parameter_row:
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
            maximum=2,
            value=1.005,
            step=0.001,
            interactive=True,
            label="Repetition_penalty",
        )
        chat_do_sample = gr.Checkbox(interactive=True,
                                        value=True,
                                        label="Do_sample")
        
    return chat_max_output_tokens, chat_num_beams, chat_repetition_penalty, chat_do_sample    



def build_single_chatbot(chatbot_id): 
    chatbot_id = str(chatbot_id)
    gr.Markdown("# <h3>🕵️‍♂️ 自研Agent模型</h3>")
    with gr.Group(elem_id="share-region-named"+chatbot_id):
        with gr.Row(elem_id="model_selector_row"+chatbot_id):
            model_selector = build_model_selector()
            model_selector.change()
            b = gr.Button("Refresh", scale=1)
            b.click(build_model_selector, [], model_selector)
        json_viewer = gr.Dataframe(visible=False)

        with gr.Accordion("对话历史", open=True, visible=True) as chatbot_row:
            chatbot = gr.Chatbot(
                elem_id="chatbot"+chatbot_id,
                label="Model "+chatbot_id,
                height=600,
            )
    return model_selector, chatbot, json_viewer       

def build_single_chatbot2(chatbot_id): 
    chatbot_id = str(chatbot_id)
    gr.Markdown("# <h3>🕵️‍♂️ 高校Agent模型</h3>")
    with gr.Group(elem_id="share-region-named"+chatbot_id):
        with gr.Row(elem_id="model_selector_row"+chatbot_id):
            model_selector = build_model_selector2()
            model_selector.change()
            b = gr.Button("Refresh", scale=1)
            b.click(build_model_selector2, [], model_selector)

        # with gr.Accordion("结构化数据", open=True, visible=True) as json_row:
        #     json_viewer = gr.Dataframe()
        json_viewer = gr.Dataframe(visible=False)

        with gr.Accordion("对话历史", open=True, visible=True) as chatbot_row:
            chatbot = gr.Chatbot(
                elem_id="chatbot"+chatbot_id,
                label="Model "+chatbot_id,
                height=600,
            )
    
    return model_selector, chatbot, json_viewer   

def build_single_chatbot3(chatbot_id): 
    chatbot_id = str(chatbot_id)
    gr.Markdown("# <h3>🕵️‍♂️ 中控模型</h3>")
    with gr.Group(elem_id="share-region-named"+chatbot_id):
        with gr.Row(elem_id="model_selector_row"+chatbot_id):
            model_selector = build_model_selector3()
            model_selector.change()
            b = gr.Button("Refresh", scale=1)
            b.click(build_model_selector3, [], model_selector)

        json_viewer = gr.Dataframe(visible=False)

        with gr.Accordion("对话历史", open=True, visible=True) as chatbot_row:
            chatbot = gr.Chatbot(
                elem_id="chatbot"+chatbot_id,
                label="Model "+chatbot_id,
                height=600,
            )
    
    return model_selector, chatbot, json_viewer          


def build_model_selector():
    tab = '单意图任务'
    model_names = ["None"]
    pload = {
        "tab": tab
    }
    # model_names += requests.post(f"http://{args.controller_host}:21001/list_models",json=pload).json()['model_names']
    model_names += requests.post(f"http://localhost:21001/list_models",json=pload).json()['model_names']
    # model_names = ["自研Agent模型1", "自研Agent模型2", "自研Agent模型3"]

    model_selector = gr.Dropdown(
                choices= model_names,
                value=model_names[-1],
                interactive=True,
                show_label=False,
                container=False,
                scale=5
            )
    return model_selector

def build_model_selector2():
    tab = '单意图任务'
    model_names = ["None"]
    pload = {
        "tab": tab
    }
    # model_names += requests.post(f"http://{args.controller_host}:21001/list_models",json=pload).json()['model_names']
    # model_names = ["高校Agent模型1", "高校Agent模型2", "高校Agent模型3"]
    model_names += requests.post(f"http://localhost:21001/list_models",json=pload).json()['model_names']

    model_selector = gr.Dropdown(
                choices= model_names,
                value=model_names[-1],
                interactive=True,
                show_label=False,
                container=False,
                scale=5
            )
    return model_selector

def build_model_selector3():
    tab = '单意图任务'
    model_names = ["None"]
    pload = {
        "tab": tab
    }
    # model_names += requests.post(f"http://{args.controller_host}:21001/list_models",json=pload).json()['model_names']
    model_names += requests.post(f"http://localhost:21001/list_models",json=pload).json()['model_names']
    # model_names = ["中控模型1", "中控模型2", "中控模型3"]

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
    worker_addr = requests.post(controller_address, json=pload).json()['address']
    print(worker_addr)
    try:
        # resp
        response = requests.post(worker_addr + "/worker_generate",
            headers=headers, json=pload, stream=False, timeout=10).json()
    except:
        response = "似乎卡住了，再试一下呢"
    print(response)
    return response    
