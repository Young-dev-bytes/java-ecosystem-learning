package java_basic._1107_string.a;

import java.util.Arrays;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/7 17:03
 */

public final class StringTest {

    private final char value[];

    public StringTest(char[] value) {
        this.value = value;
    }

    public void updateValue() {
        // this.value = new char[]{'a'};
    }

    @Override
    public String toString() {
        return "StringTest{" +
                "value=" + Arrays.toString(value) +
                '}';
    }
}
