
import gradio as gr
from PIL import Image


def add_numbers(x, y):
    return x + y

def reverse_text(input_text):
    return input_text[::-1]    

def slider_example(value):
    return f"Slider current value is: {value}"


def dropdown_example(value):
    return f"Current value is: {value}"

options = ["A", "B", "C"]

def convert(image_path):
    image = Image.open(image_path)
    return image.convert("L")

def number_details(number):
    details = {
        "original": number,
        "squared": number ** 2,
        "sqrt": number ** 0.5,
        "is_even": number % 2 == 0
    }  
    return details  

def classify_number(number):
    if number > 0:
        return "Positive"
    elif number < 0:
        return "Negative"
    else:
        return "Zero"            

# iface = gr.Interface(fn = add_numbers, inputs = [gr.Number(10), gr.Number()], outputs = gr.Number())
# iface = gr.Interface(fn = reverse_text, inputs = gr.Text(), outputs = gr.Text())
# iface = gr.Interface(fn = slider_example, inputs = gr.Slider(minimum=1, maximum=20), outputs = gr.Text())
# iface = gr.Interface(fn = dropdown_example, inputs = gr.Dropdown(choices = options), outputs = gr.Text())
# iface = gr.Interface(fn = convert, inputs = gr.Image(type = 'filepath'), outputs = gr.Image())
# iface = gr.Interface(fn = number_details, inputs = gr.Number(), outputs = gr.Json())
iface = gr.Interface(fn = classify_number, inputs = gr.Number(), outputs = gr.Label())
iface.launch() 