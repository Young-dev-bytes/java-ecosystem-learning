package design_patterns.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/26 19:49
 */

public class History {

    private List<EditorState> states = new ArrayList<>();

    public void push(EditorState state) {
        states.add(state);
    }

    public EditorState pop() {
        Integer lastIndex = states.size() - 1;
        EditorState lastState = states.get(lastIndex);
        states.remove(lastState);
        return lastState;
    }


}
