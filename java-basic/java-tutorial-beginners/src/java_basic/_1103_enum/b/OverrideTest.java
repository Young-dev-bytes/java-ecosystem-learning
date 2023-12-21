package java_basic._1103_enum.b;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/3 17:10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface OverrideTest {
}
