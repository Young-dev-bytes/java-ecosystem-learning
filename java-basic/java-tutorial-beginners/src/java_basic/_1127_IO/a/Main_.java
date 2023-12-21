package java_basic._1127_IO.a;

import java.io.File;
import java.io.IOException;

public class Main_ {
    public static void main(String[] args) throws IOException {
        create01();

    }

    public static void create01() throws IOException {
        String filePath = "/Users/share/Desktop/new1.txt";
        File file = new File(filePath);
        file.createNewFile();
    }

    public static void create02() throws IOException {
        File parentFile = new File("/Users/share/Desktop");
        String childFilePath = "new4.txt";
        File file = new File(parentFile, childFilePath);
        file.createNewFile();
        System.out.println("create successfully");
    }

    public static void create03() throws IOException {
        String parentFilePath = "/Users/share/Desktop";
        String childFilePath = "new5.txt";
        File file = new File(parentFilePath, childFilePath);
        file.createNewFile();
    }
}
