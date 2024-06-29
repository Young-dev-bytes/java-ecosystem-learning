import gradio as gr


with gr.Blocks() as demo:
    # with gr.Row():
    #     text1 = gr.Text(value = "OUTPUT ONE")
    #     text2 = gr.Text(value = "OUTPUT TWO")
    # with gr.Row():
    #     text3 = gr.Text(value = "BOTTOM THREE")    
    with gr.Tab('Tab one'):
        with gr.Row():
            with gr.Column(scale=2):
                text1 = gr.Text('Row 0 Col 0 - Com - 1')
                text2 = gr.Text('Row 0 Col 0 - Com - 2')
            with gr.Column(scale=1):
                text3 = gr.Text('Row 0 Col 1')
        with gr.Row():
            text4 = gr.Text(value = "Young") 
    with gr.Tab('Tab two'):
        with gr.Row():
            text5 = gr.Text(value = "Young2", scale=2) 
            text6 = gr.Text(value = "Young3", scale=1) 
    with gr.Tab('Tab three'):        
        gr.Label('Label Here')
        with gr.Accordion('Accordion here', open=True):
            gr.Image()


demo.launch()



