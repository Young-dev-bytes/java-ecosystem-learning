package design_patterns.memento;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/26 19:45
 */

public class EditorState {

    private final String content;

    public EditorState(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
