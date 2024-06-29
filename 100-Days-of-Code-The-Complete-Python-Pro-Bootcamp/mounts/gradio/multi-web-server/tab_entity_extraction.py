
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




