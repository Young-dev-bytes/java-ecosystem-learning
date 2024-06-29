import gradio as gr


with gr.Blocks() as demo:
    with gr.Group():
        gr.Button('Button Comp One')
        gr.Button('Button Comp Two')
        gr.Image()
        
    gr.Image()

demo.launch()



