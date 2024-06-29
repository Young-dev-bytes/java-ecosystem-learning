import gradio as gr


css = """
.yourclass {
    height: 100px;
    background-color:red;
    # width:20px;
}
"""

with gr.Blocks(css=css) as demo:
    with gr.Row(elem_classes=['yourclass']):
        # gr.Image(height=900, width=20)
        gr.Image()

demo.launch()



