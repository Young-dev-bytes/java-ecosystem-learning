package java_basic._1120_gernic.b;

import java.util.Arrays;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/20 16:24
 */

public class Demo_ {
    public static void main(String[] args) {

        Tiger<Double, String, Integer> g = new Tiger<>("john");
        g.setT(10.9);
        // g.setT("yy");
        System.out.println(g);

        Tiger g2 = new Tiger<>("john~~");
        g2.setT("yy");
        System.out.println(g2);


    }
}

class Tiger<T, R, M> {

    R r;
    M m;
    T t;
    T[] ts;


    public Tiger(R r) {
        this.r = r;
    }

    public Tiger(R r, M m, T t, T[] ts) {
        this.r = r;
        this.m = m;
        this.t = t;
        this.ts = ts;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public M getM() {
        return m;
    }

    public void setM(M m) {
        this.m = m;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public T[] getTs() {
        return ts;
    }

    public void setTs(T[] ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "Tiger{" +
                "r=" + r +
                ", m=" + m +
                ", t=" + t +
                ", ts=" + Arrays.toString(ts) +
                '}';
    }
}
