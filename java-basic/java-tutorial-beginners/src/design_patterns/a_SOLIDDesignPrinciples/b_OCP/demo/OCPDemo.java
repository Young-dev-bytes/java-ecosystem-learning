package design_patterns.a_SOLIDDesignPrinciples.b_OCP.demo;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/6 20:28
 */

enum Color {
    RED, GREEN, BLUE
}

enum Size {
    SMALL, MEDIUM, LARGE, HUGE
}

enum Price {
    LOW, HIGH
}

class Product {
    private String name;
    private Color color;
    private Size size;
    private Price price;


    public Product(String name, Color color, Size size, Price price) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}

class ProductFilter {

    public Stream<Product> filterByColor(List<Product> products, Color color) {
        return products.stream().filter(p -> p.getColor() == color);
    }

    public Stream<Product> filterBySize(List<Product> products, Size size) {
        return products.stream().filter(p -> p.getSize() == size);
    }

    public Stream<Product> filterByPrice(List<Product> products, Price price) {
        return products.stream().filter(p -> p.getPrice() == price);
    }

    public Stream<Product> filterByColorAndSize(List<Product> products, Color color, Size size) {
        return products.stream().filter(p -> p.getSize() == size && p.getColor() == color);
    }

    public Stream<Product> filterByColorAndPrice(List<Product> products, Color color, Price price) {
        return products.stream().filter(p -> p.getPrice() == price && p.getColor() == color);
    }

    public Stream<Product> filterBySizeAndPrice(List<Product> products, Size size, Price price) {
        return products.stream().filter(p -> p.getSize() == size && p.getPrice() == price);
    }

    public Stream<Product> filterBySizeAndPriceAndColor(List<Product> products, Size size, Price price, Color color) {
        return products.stream().filter(p -> p.getSize() == size && p.getPrice() == price && p.getColor() == color);
    }
}


// extension
interface Specification<T> {
    boolean isSatisfied(T item);
}

class AndSpecification<T> implements Specification<T> {
    private Specification<T> first, second;

    public AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}

interface Filter<T> {
    Stream<T> filter(List<T> items, Specification<T> spec);
}

class ColorSpecification implements Specification<Product> {
    private Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product p) {
        return p.getColor() == color;
    }
}

class SizeSpecification implements Specification<Product> {

    private Size size;

    public SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product p) {
        return p.getSize() == size;
    }
}

class PriceSpecification implements Specification<Product>{
    private Price price;

    public PriceSpecification(Price price) {
        this.price = price;
    }


    @Override
    public boolean isSatisfied(Product p) {
        return p.getPrice() == price;
    }
}

class BetterFilter implements Filter<Product> {

    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
        return items.stream().filter(p -> spec.isSatisfied(p));
    }
}


public class OCPDemo {
    public static void main(String[] args) {

        Product apple = new Product("apple", Color.RED, Size.SMALL, Price.HIGH);
        Product tree = new Product("tree", Color.GREEN, Size.LARGE, Price.LOW);
        Product house = new Product("house", Color.GREEN, Size.LARGE, Price.HIGH);
        Product clothes = new Product("clothes", Color.BLUE, Size.LARGE, Price.HIGH);

        // List<Product> products = List.of(apple, tree, house, clothes);
        List<Product> products = null;


        ProductFilter pf = new ProductFilter();
        System.out.println("Red products (old): ");
        pf.filterByColor(products, Color.RED).forEach(p -> System.out.println("-" + p.getName() + " is red"));
        System.out.println("Large products (old): ");
        pf.filterBySize(products, Size.LARGE).forEach(p -> System.out.println("-" + p.getName() + " is large"));


        // ^^ BEFORE

        // vv AFTER
        BetterFilter bf = new BetterFilter();
        System.out.println("Green products (new)");
        bf.filter(products, new ColorSpecification(Color.BLUE)).
                forEach(p -> System.out.println(" - " + p.getName() + " is blue"));


        bf.filter(products,
                new AndSpecification<>(new ColorSpecification(Color.BLUE), new SizeSpecification(Size.LARGE)))
                .forEach(p -> System.out.println(" - " + p.getName() + " is large and blue"));

        bf.filter(products,
                new AndSpecification<>(new ColorSpecification(Color.RED), new PriceSpecification(Price.HIGH)))
                .forEach(p -> System.out.println(" - " + p.getName() + " is red and high"));

        bf.filter(products,
                new AndSpecification<>(new SizeSpecification(Size.LARGE), new PriceSpecification(Price.HIGH)))
                .forEach(p -> System.out.println(" - " + p.getName() + " is large and high"));
    }
}
