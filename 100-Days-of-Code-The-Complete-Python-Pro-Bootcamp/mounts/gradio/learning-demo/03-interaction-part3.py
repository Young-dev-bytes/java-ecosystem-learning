import gradio as gr
from PIL import Image



# def multiply(x,y):
#     return x*y

# def add(x,y):
#     return x+y
class Operations():
    def __init__(self):
        print("Operation methods are ready!")

    def multiply(self, x,y):
        return x*y

    def add(self, x,y):
        return x+y    
        
operations_object = Operations()


with gr.Blocks() as demo:
    with gr.Row():
        x = gr.Slider()
        y = gr.Slider()
    with gr.Row():
        result_of_add = gr.Text()
        result_of_multiply = gr.Text()
    with gr.Row():
        button_mul = gr.Button('Multiply')
        button_add = gr.Button('Add')


    # button_mul.click(fn=multiply, inputs=[x,y], outputs=[result_of_multiply])     
    # button_add.click(fn=add, inputs=[x,y], outputs=[result_of_add])  

    button_mul.click(fn=operations_object.multiply, inputs=[x,y], outputs=[result_of_multiply])     
    button_add.click(fn=operations_object.add, inputs=[x,y], outputs=[result_of_add])     


demo.launch()    



