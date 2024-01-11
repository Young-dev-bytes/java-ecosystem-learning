package com.design.pattern.solid.ocp;

import java.util.List;
import java.util.stream.Stream;

// open close principle
enum Color {
    RED, GREEN, BLUE
}

enum Size {
    SMALL, MEDIUM, LARGE, YUGE
}

enum Price {
    CHEAP, EXPENSIVE
}

class Product {
    public String name;
    public Color color;
    public Size size;
    public Price price;

    public Product(String name, Color color, Size size, Price price) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.price = price;
    }
}

class ProductFilter {
    public Stream<Product> filterByColor(List<Product> products, Color color) {
        return products.stream().filter(p -> p.color == color);
    }

    public Stream<Product> filterBySize(List<Product> products, Size size) {
        return products.stream().filter(p -> p.size == size);
    }

    public Stream<Product> filterByPrice(List<Product> products, Price price) {
        return products.stream().filter(p -> p.price == price);
    }

    public Stream<Product> filterBySizeAndColor(List<Product> products, Size size, Color color) {
        return products.stream().filter(p -> p.size == size && p.color == color);
    }

    // filterBySizeAndPrice
    // filterByColorAndPrice
    // filterBySizeAndColorAndPrice

    // state space explosion
    // 3 criteria = 7 methods
}

// we introduce two new interfaces that are open for extension


class OCPDemo {
    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.GREEN, Size.SMALL, Price.CHEAP);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE, Price.CHEAP);
        Product house = new Product("House", Color.BLUE, Size.LARGE, Price.EXPENSIVE);

        List<Product> products = List.of(apple, tree, house);

        ProductFilter pf = new ProductFilter();

        System.out.println("Green products (old):");
        pf.filterByColor(products, Color.GREEN)
                .forEach(p -> System.out.println(" - " + p.name + " is green"));

        System.out.println("Small products (old):");
        pf.filterBySize(products, Size.SMALL)
                .forEach(p -> System.out.println(" - " + p.name + " is small"));

        System.out.println("expensive products (old):");
        pf.filterByPrice(products, Price.EXPENSIVE)
                .forEach(p -> System.out.println(" - " + p.name + " is expensive"));

        // ^^ BEFORE


        // vv AFTER


    }
}
