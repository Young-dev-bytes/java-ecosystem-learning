import os
import argparse
import gradio as gr
from gradio.themes.utils import colors

from tab_entity_extraction import build_entity_extraction_tab
from tab_event_extraction import build_event_extraction_tab

# if __name__ == "__main__":


# 设置临时目录路径
os.environ['GRADIO_TEMP_DIR'] = '/mnt/nas/mm/ie_env/tmp'
parser = argparse.ArgumentParser()
parser.add_argument("--private", default=False, action='store_true')
parser.add_argument("--port", default=7860, type=int)
parser.add_argument("--controller_host", default="127.0.0.1", type=str)
args = parser.parse_args()

theme=gr.themes.Soft(primary_hue=colors.gray, neutral_hue=colors.neutral)
js_func = """
function refresh() {
    const url = new URL(window.location);

    if (url.searchParams.get('__theme') !== 'dark') {
        url.searchParams.set('__theme', 'dark');
        window.location.href = url.href;
    }
}
"""
with gr.Blocks(theme=theme,js=js_func) as demo:
    gr.Markdown("""荣耀智慧大模型demo平台""")
    with gr.Tabs(elem_classes="tab-buttons") as tabs:
        with gr.TabItem("实体提取", elem_id="chat", id=1):
            build_entity_extraction_tab()

        with gr.TabItem("事件提取", elem_id="chat", id=2):
            build_event_extraction_tab()
        
        # with gr.TabItem("屏幕理解", elem_id="info", id=3):
        #     build_screen_comprehension_tab()

        # with gr.TabItem("抽象语义提取", elem_id="info", id=4):
        #     build_abstract_semantic_extraction_tab()

        # with gr.TabItem("指令图像编辑", elem_id="info", id=5):
        #     build_picture_edit_tab()

        # with gr.TabItem("About Us", elem_id="info", id=0):
        #     build_about_us_tab()

if args.private:
    demo.queue().launch(share=False, server_name="127.0.0.1", server_port=args.port, max_threads=1)
else:
    demo.queue().launch(share=True, server_name="0.0.0.0", server_port=args.port, max_threads=1)

