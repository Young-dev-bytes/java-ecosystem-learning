import gradio as gr
from PIL import Image



def make_grayscale(image_path):
    
    image = Image.open(image_path)
    image_grayscale = image.convert("L")

    return image_grayscale, 'Image converted'


with gr.Blocks() as demo:
    with gr.Row():
        input_image = gr.Image(type='filepath')
        output_image = gr.Image()
    with gr.Row():
        log = gr.Text() 
        submit = gr.Button(value='Convert to grayscale')  

    submit.click(fn=make_grayscale, inputs=input_image, outputs=[output_image,log])     


demo.launch()    



