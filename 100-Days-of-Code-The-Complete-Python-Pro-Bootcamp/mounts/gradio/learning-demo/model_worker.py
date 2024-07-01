"""
A model worker executes the model.
"""
import argparse
from fastapi import FastAPI, Request, BackgroundTasks
from fastapi.responses import StreamingResponse, JSONResponse
import requests
import uvicorn
import time
import threading


class ModelWorker:
    def __init__(self, host, port,register_host, controller_address, model, model_name,model_type,tab):
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
        self._register_to_controller()
        threading.Thread(target=self._send_heart_beat_to_controller).start()
        

    def _register_to_controller(self):

        url = self.controller_address + "/register_worker"
        r = requests.post(url, json=self.worker_info)
        print(r)
        assert r.status_code == 200

    def _send_heart_beat_to_controller(self):
        url = self.controller_address + "/heart_beat"
        while True:
            try:
                requests.post(url, json=self.worker_info)
            except:
                pass
            time.sleep(60)

    def generate(self, params):
        # text = params['text']
        # image = params['image']

        print(params)

        demo = [
            # {"user": "打开闹钟", "assistant":""}
            {"user": "今天天气怎么样", "assistant":""}
        ]
        result = self.model.llm_predict1(demo)
        return result

app = FastAPI()

@app.post("/worker_generate")
async def generate_stream(request: Request):
    params = await request.json()
    response = model_worker.generate(params)
    print(response)
    return JSONResponse(response)

    

if __name__ == "__main__":

    from model_adapters import InternVLModelAdapter

    parser = argparse.ArgumentParser()

    parser.add_argument("--model-path", type=str, default="/mnt/nas/mm/ie_env/yh/demo_model/internvl_chat_v1_5")
    parser.add_argument("--device", type=str, default="cuda:7")

    parser.add_argument("--host", type=str, default="10.71.110.42")
    parser.add_argument("--register-host", type=str, default="10.71.110.42")

    parser.add_argument("--port", type=int, default=21002)

    parser.add_argument("--controller-address", type=str,
        default="http://localhost:21001")
    
    parser.add_argument("--model-name", type=str, default="实体抽取 v1.4")
    parser.add_argument("--model-type", type=str, default="internVL")
    parser.add_argument("--tab", type=str, default="其他")
    

    args = parser.parse_args()
    if args.model_type == 'internVL':
        model = InternVLModelAdapter(args.model_path, args.device)

    model_worker = ModelWorker(
        host=args.host, 
        register_host=args.register_host,
        port=args.port, 
        controller_address=args.controller_address,
        model=model,
        model_name=args.model_name,
        model_type=args.model_type,
        tab=args.tab)

    uvicorn.run(app, host=args.host, port=args.port, log_level="info")
