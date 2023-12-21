package java_basic._1109_collection.arrayList.a;


import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/9 20:17
 */

public class Dog implements Iterable<Dog> {
    @Override
    public Iterator<Dog> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Dog> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Dog> spliterator() {
        return Iterable.super.spliterator();
    }
}
