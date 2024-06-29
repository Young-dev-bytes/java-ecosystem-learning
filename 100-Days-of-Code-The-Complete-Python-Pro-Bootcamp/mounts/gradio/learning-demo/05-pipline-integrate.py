
# Load model directly
from transformers import pipeline
import gradio as gr

sentiment_analysis = pipeline('sentiment-analysis')
sentiment_analysis("I am sad")

def predict_sentiment(text):
    result = sentiment_analysis(text)
    return result[0]['label'],result[0]['score']

predict_sentiment("I am happy")

# iface = gr.Interface(fn = predict_sentiment, inputs = gr.Textbox(lines=2,placeholder='Type your text here!'), outputs = [gr.Text(label='Sentiment'), gr.Text(label='Score')])
# iface.launch()

demo = gr.load('microsoft/resnet-50')
demo.launch()