import gradio as gr

def build_event_extraction_tab():
     with gr.Row():
        gr.Markdown(
        """
            ### Model Author: 杨  赫(00031743)
            ### Demo Author: 黄逸嘉(00031952)、张晟辉(00032043)
        """)

def build_single_chatbot(chatbot_id):
    chatbot_id = str(chatbot_id)
    _id = gr.Textbox(visible=False, value=chatbot_id)
    gr.Markdown(f"# <h3>🕵️‍♂️{label_model[int(chatbot_id)]}</h3>")
    with gr.Group(elem_id="share-region-named" + chatbot_id):
        with gr.Row(elem_id="model_selector_row" + chatbot_id):
            model_selector = build_model_selector(chatbot_id)
            model_selector.change()
            btn_state = gr.Button("模型未加载", scale=1, interactive=False)
            button_state = gr.State("load")

        with gr.Accordion("管理模型", open=True, visible=True):
            with gr.Row(elem_id="model_selector_row" + chatbot_id):
                load_btn = gr.Button("加载模型", scale=1)
                unload_btn = gr.Button("卸载模型", scale=1, interactive=False)


                load_btn.click(
                    load_model_btn_operate,
                    inputs=[model_selector],
                    outputs=[
                        btn_state,
                        button_state,
                        model_selector,
                        load_btn,
                        unload_btn,
                    ],
                    show_progress="hidden",
                )
                unload_btn.click(
                    unload_model_btn_operate,
                    inputs=[model_selector],
                    outputs=[
                        btn_state,
                        button_state,
                        model_selector,
                        unload_btn,
                        load_btn,
                    ],
                    show_progress="hidden",
                )

        with gr.Accordion("对话历史", open=True, visible=True) as chatbot_row:
            chatbot = gr.Chatbot(
                elem_id="chatbot" + chatbot_id,
                elem_classes=["chatbot" + chatbot_id],
                label="Model " + chatbot_id,
                height=600,
            )

    return model_selector, chatbot


def load_model_btn_operate(model_name):
    resp_load_model = load_model(model_name)
    print(resp_load_model)
    if resp_load_model["message"] == "ok":
        while True:
            time.sleep(4)
            resp_status_load = query_model_status(model_name)["message"][0]
            print("resp_status_load:")
            print(resp_status_load)
            if resp_status_load == "Running":
                yield [
                    gr.update(value="模型加载成功", interactive=False),
                    "success",
                    gr.update(interactive=False),
                    gr.update(interactive=False),
                    gr.update(interactive=True),
                ]
                break
            elif (
                resp_status_load == "Pending" or resp_status_load == "ContainerCreating"
            ):
                print("Pending, ContainerCreating")
                yield [
                    gr.update(value="模型加载中...", interactive=False),
                    "loading",
                    gr.update(interactive=False),
                    gr.update(interactive=False),
                    gr.update(interactive=False),
                ]
                break
            elif (
                resp_status_load == "Terminating"
                or resp_status_load == "CrashLoopBackOff"
            ):
                print("Terminating, CrashLoopBackOff ")
                yield [
                    gr.update(value="模型加载失败", interactive=False),
                    "load_failed",
                    gr.update(interactive=False),
                    gr.update(interactive=False),
                    gr.update(interactive=False),
                ]
                break
    else:
        yield [
            gr.update(value="加载失败,请重试", interactive=True),
            "load_failed",
            gr.update(interactive=True),
        ]


def unload_model_btn_operate(model_name):
    resp_unload_model = unload_model(model_name)
    print("resp_unload_model")
    print(resp_unload_model)
    if resp_unload_model["message"] == "ok":
        while True:
            time.sleep(4)
            resp_status_load = query_model_status(model_name)["message"][0]
            print("resp_status_load:")
            print(resp_status_load)
            if resp_status_load == "Terninating":
                yield [
                    gr.update(value="模型卸载成功", interactive=False),
                    "success",
                    gr.update(interactive=False),
                    gr.update(interactive=False),
                    gr.update(interactive=True),
                ]
                break
    else:
        yield [
            gr.update(value="卸载失败,请重试", interactive=True),
            "unloaded_failed",
            gr.update(interactive=False),
            gr.update(interactive=False),
        ]


async def listen_load_model_btn_status(model_name):
    print("listen_load_model_btn_status:")
    while True:
        await asyncio.sleep(1)  # 非阻塞的等待
        resp_status_load = query_model_status(model_name)["message"]
        print(resp_status_load)
        count += 1
        print(count)
        resp_status_load = "Pending"

        if resp_status_load == "Running":
            yield [
                gr.update(value="模型加载成功", interactive=False),
                "success",
                gr.update(interactive=False),
            ]
            break
        elif resp_status_load == "Pending":
            print(123)
            yield [
                gr.update(value="模型加载中...{count}", interactive=False),
                "loading",
                gr.update(interactive=False),
            ]
            if count >= 5:  # 假设任务在10秒后完成
                yield [
                    gr.update(value="模型加载成功", interactive=False),
                    "success",
                    gr.update(interactive=False),
                ]
                break


def change_btn_unload(model_name, button_state):
    print("change btn unload")
    print(f"button_state: {button_state}")
    if button_state == "success":
        return [
            gr.update(value="卸载模型", interactive=True),
            "unload",
            gr.update(interactive=False),
        ]
    if button_state == "unload_success":
        return [
            gr.update(value="加载模型", interactive=True),
            "load",
            gr.update(interactive=True),
        ]


def load_model(model_name):
    print("starting load model: ")
    print(model_name)
    args = get_args()
    load_model_address = f"http://{args.controller_host}:21001/load_model"
    path = f"/root/demo/{args.demo_project_name}/yaml/{model_name}.yaml"
    # pload = {"path": "/root/demo/aicentralcontrol/web-server/web.yaml"}
    pload = {"path": path}
    # load model
    try:
        response = requests.post(load_model_address, json=pload).json()
    except:
        response = {"message": "loading model failed"}
    print(response)
    return response


def unload_model(model_name):
    print("start unload model")
    args = get_args()
    unload_model_addr = f"http://{args.controller_host}:21001/unload_model"
    # aicentralcontrol-qwen-7b-model-server-deployment
    model_name = "qwen-7b"
    deployment_name = f"{args.demo_project_name}-{model_name}-model-load-server-deployment"
    pload = {"deployment_name": deployment_name}
    print(deployment_name)
    # unload model
    try:
        response = requests.post(unload_model_addr, json=pload).json()
    except:
        response = {"message": "unload model failed"}
    print(response)
    return response


def query_model_status(model_name):
    print("start query model status:")
    args = get_args()
    load_model_status_addr = f"http://{args.controller_host}:21001/status_model"
    # aicentralcontrol-qwen-7b-model-load-server-deployment
    deployment_name = f"{args.demo_project_name}-{model_name}-model-load-server-deployment"
    pload = {"deployment_name": deployment_name}
    print(deployment_name)
    # load model status
    try:
        response = requests.post(load_model_status_addr, json=pload).json()
    except:
        response = {"message": "query model status failed"}
    print(response)
    if "detail" in response:
        return {"message": "not found"}
    else:    
        return response


def text_in_change(text):
    return text


def clear_history(chat_history1, chat_history2, chat_history3):
    return [None, None, None]


def add_text(text, model_selector1, model_selector2, model_selector3):
    chat_history_list = [None, None, None]
    model_selectors = [model_selector1, model_selector2, model_selector3]
    # content of submit
    print(text)
    # submiting models
    print(model_selectors)

    for i in range(num_chatbots):
        if model_selectors[i] != "None":
            chat_history = [text, None, None]
            chat_history_list[i] = chat_history

    # [[['今天工作怎么样', None]], [['今天工作怎么样', None]], ""]
    print(chat_history_list)
    return chat_history_list


def predict(
    model_selector1,
    model_selectors2,
    model_selectors3,
    chat_history1,
    chat_history2,
    chat_history3,
    chat_textbox,
):
    model_selectors = [model_selector1, model_selectors2, model_selectors3]
    chat_history_list = [chat_history1, chat_history2, chat_history3]
    for i in range(num_chatbots):
        model_selected = model_selectors[i]
        if model_selected == "None":
            chat_history = [[None, None]]
        else:
            text = chat_textbox
            # ['查天气(日期=今天) THEN system.if() THEN 提醒()']
            response = predict_single_http(model_selected, text)
            chat_history = [[text, response[0]]]
            chat_history_list[i].append(chat_history[0])
    # [[['你好', 'system.if(其他条件=你好) THEN 提醒()']], [], []]
    # return [[['123','456'],['789','891']], None, None]
    return chat_history_list


def build_model_selector(chatbot_id):
    tab = ""
    model_names = ["None"]
    model_type = model_type_def[chatbot_id]
    pload = {"tab": "", "model_type": model_type}
    headers = {"Content-Type": "application/json;charset=UTF-8"}
    args = get_args()
    model_names += requests.post(
        f"http://{args.controller_host}:21001/list_models", json=pload
    ).json()["model_names"]
    model_selector = gr.Dropdown(
        choices=model_names,
        value=model_names[-1],
        interactive=True,
        show_label=False,
        container=False,
        scale=5,
    )
    return model_selector


def predict_single_http(model_name, text):

    args = get_args()
    controller_address = f"http://{args.controller_host}:21001/get_worker_address"
    headers = {"User-Agent": "Demo Client"}
    pload = {"model": model_name, "text": text}
    # model server addr
    worker_addr = requests.post(controller_address, json=pload).json()["address"]
    print(worker_addr)
    try:
        # resp
        response = requests.post(
            worker_addr + "/worker_generate",
            headers=headers,
            json=pload,
            stream=False,
            timeout=10,
        ).json()
    except:
        response = "似乎卡住了，再试一下呢"
    return response
-------


"""
A model worker executes the model.
"""
from fastapi.responses import StreamingResponse, JSONResponse
from constants import CONTROLLER_HEART_BEAT_EXPIRATION
from fastapi import FastAPI, Request, BackgroundTasks
from utils import build_logger, server_error_msg

import argparse
import requests
import uvicorn
import time
import threading
import logging

logger = build_logger("model", "model.log")

class ModelWorker:
    def __init__(self, host, port, model, register_host, controller_address, model_name, model_type, tab):
        self.host = host
        self.port = port
        self.register_host = register_host
        self.worker_address = f"http://{register_host}:{port}"
        self.controller_address = controller_address
        self.model = model
        self.model_name = model_name
        self.model_type = model_type
        self.tab = tab
        self.worker_info = {
                "model_name": self.model_name,
                "worker_address": self.worker_address,
                "model_type": self.model_type,
                "tab": self.tab
            }
        self._check_register_info_to_controller()
        # threading.Thread(target=self._send_heart_beat_to_controller).start()

    def _check_register_info_to_controller(self):
        url = self.controller_address + "/register_check_info"
        response = requests.post(url, json=self.worker_info)
        assert response.status_code == 200

    def _send_heart_beat_to_controller(self):
        url = self.controller_address + "/heart_beat"
        while True:
            try:
                requests.post(url, json=self.worker_info)
            except:
                pass
            time.sleep(60)

    def generate(self, params):
        text = params['text']
        print(params)
        print(text)
        # demo modelname time 1
        curTime = time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()+28800))
        logger.info(f"当前模型名称是：{self.model_name}，模型类型是：{self.model_type}，时间是：{curTime}")
        demo = [
            {"user": text, "assistant":""}
        ]
        return self.model.llm_predict1(demo)


app = FastAPI()

@app.post("/worker_generate")
async def generate_stream(request: Request):
    params = await request.json()
    response = model_worker.generate(params)
    return JSONResponse(response)

if __name__ == "__main__":

    from model_adapters import InternVLModelAdapter

    parser = argparse.ArgumentParser()
    parser.add_argument("--model-path", type=str, default="")
    parser.add_argument("--device", type=str, default="cuda:0")
    parser.add_argument("--host", type=str, default="127.0.0.1")
    parser.add_argument("--register-host", type=str, default="127.0.0.1")
    parser.add_argument("--port", type=int, default=21002)
    parser.add_argument("--controller-address", type=str, default="http://localhost:21001")
    parser.add_argument("--model-name", type=str, default="")
    parser.add_argument("--model-type", type=str, default="")
    parser.add_argument("--tab", type=str, default="")
    

    args = parser.parse_args()
    if args.model_type == 'self_develop':
        model = InternVLModelAdapter(args.model_path, args.device)

    if args.model_type == 'college':
        model = InternVLModelAdapter(args.model_path, args.device)    

    model_worker = ModelWorker(
        host = args.host, 
        port = args.port, 
        model = model,
        register_host = args.register_host,
        controller_address = args.controller_address,
        model_name = args.model_name,
        model_type = args.model_type,
        tab = args.tab)
    uvicorn.run(app, host=args.host, port=args.port, log_level="info")
ーーーーー

CONTROLLER_HEART_BEAT_EXPIRATION = 30

WORKER_HEART_BEAT_INTERVAL = 15

LOGDIR = "."

# Model Constants
IGNORE_INDEX = -100
IMAGE_TOKEN_INDEX = -200
DEFAULT_IMAGE_TOKEN = "<image>"
DEFAULT_IMAGE_PATCH_TOKEN = "<im_patch>"
DEFAULT_IM_START_TOKEN = "<im_start>"
DEFAULT_IM_END_TOKEN = "<im_end>"



