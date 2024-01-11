package com.design.pattern.solid.lsp;


public class Demo {

    public static void useIt(Rectangle rectangle) {
        int width = rectangle.getWidth();

        // boolean isSquare = rectangle instanceof Square;
        // area = width * 10

        rectangle.setHeight(10);
        System.out.println("Expected area of " + (width * 10) + ", got " + rectangle.getArea());

        /*if (!isSquare) {
            rectangle.setHeight(10);
            System.out.println("Expected area of " + (width * 10) + ", got " + rectangle.getArea());
        } else {
            System.out.println("Expected area of " + (width * width) + ", got " + rectangle.getArea());
        }*/
    }

    public static void main(String[] args) {

        Rectangle rectangle = new Rectangle(10, 20);
        useIt(rectangle);

        Rectangle sq = new Square();
        sq.setHeight(5);
        sq.setWidth(10);
        useIt(sq);


    }
}
