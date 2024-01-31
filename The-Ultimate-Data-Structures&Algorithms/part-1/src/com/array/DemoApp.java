package com;


public class DemoApp {
    public static void main(String[] args) {

        Array array = new Array(3);
        array.insert(3);
        array.insert(4);
        array.insert(5);
        array.insert(6);

        array.removeAt(0);

        System.out.println(array.indexOf(3));

        // System.out.println(array.length());

        // array.print();


    }
}
