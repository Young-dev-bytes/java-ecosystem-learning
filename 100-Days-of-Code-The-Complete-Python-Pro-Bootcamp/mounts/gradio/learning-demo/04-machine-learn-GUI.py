
# Load model directly
from transformers import AutoImageProcessor, AutoModelForImageClassification 

processor = AutoImageProcessor.from_pretrained("microsoft/resnet-50")
model = AutoModelForImageClassification.from_pretrained("microsoft/resnet-50")

def classify_image(image):
    # processing the image:
    image = processor(image, return_tensors = 'pt')['pixel_values']
    # passing the image to the model. ----> logits. 0--10000.  0.8 cat
    logits = model(image).logits
    # grab the index of the highest probability
    predicted_label = logits.argmax(-1).item()
    return model.config.id2label[predicted_label]

iface = gr.Interface(fn = classify_image, inputs = gr.Image(), outputs=gr.Label(),title = 'RES NET 18', description = 'upload an image')    

iface.launch()