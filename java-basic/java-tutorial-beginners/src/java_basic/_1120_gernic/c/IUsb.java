package java_basic._1120_gernic.c;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/20 16:46
 */

public interface IUsb<U, R> {


    R get(U u);

    void hi(R r);

    void run(R r1, R r2, U u1, U u2);

    default R method(U u) {
        return null;
    }
}

class C implements IUsb<String, Integer> {

    @Override
    public Integer get(String s) {
        return null;
    }

    @Override
    public void hi(Integer integer) {

    }

    @Override
    public void run(Integer r1, Integer r2, String u1, String u2) {

    }
}

interface MyInterface extends IUsb {

    Object get(Object o);

    @Override
    default void hi(Object o) {

    }

    @Override
    default void run(Object r1, Object r2, Object u1, Object u2) {

    }

    @Override
    default Object method(Object o) {
        return IUsb.super.method(o);
    }
}

interface MyInterfaceT extends IUsb<String, Integer> {

    @Override
    Integer get(String s);

    @Override
    void hi(Integer integer);

    @Override
    void run(Integer r1, Integer r2, String u1, String u2);

    @Override
    default Integer method(String s) {
        return IUsb.super.method(s);
    }
}

class CC implements MyInterfaceT {

    @Override
    public Integer get(String s) {
        return null;
    }

    @Override
    public void hi(Integer integer) {

    }

    @Override
    public void run(Integer r1, Integer r2, String u1, String u2) {

    }
}
