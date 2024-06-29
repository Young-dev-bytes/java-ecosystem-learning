import gradio as gr




# FUNCTION
def multiply(x,y):
    print(y)
    return x*y

with gr.Blocks() as app:
    with gr.Row():
        x_slider = gr.Slider(label="label1")
        y_slider = gr.Slider(label="label2")
    with gr.Row():
        result = gr.Text()    
    with gr.Row():
        button = gr.Button("Multiply!")    

    # x_slider.change(fn=multiply, inputs=[x_slider, y_slider], outputs=result) 
    # y_slider.change(fn=multiply, inputs=[x_slider, y_slider], outputs=result) 

    button.click(fn=multiply,inputs=[x_slider, y_slider], outputs=[result])

app.launch()



# INPUTS

# OUTPUTS

# CLICK OR CHANGE

# INTERFACE  ----> fn, inputs, outputs



# gr.interface



