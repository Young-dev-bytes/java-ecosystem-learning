package design_patterns.memento;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/26 19:30
 */

public class Editor {

    private String content;

    public EditorState createState() {
        return new EditorState(content);
    }

    public void restore(EditorState state) {
        content = state.getContent();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
