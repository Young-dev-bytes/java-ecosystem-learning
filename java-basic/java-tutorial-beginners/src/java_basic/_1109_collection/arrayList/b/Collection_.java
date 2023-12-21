package java_basic._1109_collection.arrayList.b;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/10 10:35
 */

public class Collection_ {


    @SuppressWarnings("all")
    public static void main(String[] args) {

        /*Collection col = new LinkedList();
        col.add("li");
        col.add("ki");
        col.add("ji");

        col.forEach(item -> {
            System.out.println(item);
        });*/

        List list = new ArrayList<Book>();

        list.add(new Book("红楼梦", 69.5, "曹雪芹"));
        list.add(new Book("三国 ", 34.5, "罗贯中"));
        list.add(new Book("水浒传", 23.5, "施耐庵"));
        list.add(new Book("西游记", 44.5, "吴承恩"));

        List listRes = sortByBubble(list, new Comparator<Book>() {

            @Override
            public int compare(Book book1, Book book2) {
                double priceOne = book1.getPrice();
                double priceTwo = book2.getPrice();
                return priceOne - priceTwo > 0 ? 1 : 0;
            }

        });

        listRes.forEach(item -> {
            System.out.println(item);
        });


    }

    private static List sortByBubble(List list, Comparator com) {

        int length = list.size();

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (com.compare(list.get(j), list.get(j + 1)) > 0) {
                    Object obj = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, obj);
                }
            }
        }
        return list;
    }


}

class Book {

    private String name;
    private Double price;
    private String author;

    public Book(String name, Double price, String author) {
        this.name = name;
        this.price = price;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                '}';
    }
}


