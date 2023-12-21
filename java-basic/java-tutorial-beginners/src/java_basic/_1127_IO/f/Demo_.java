package java_basic._1127_IO.f;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Demo_ {

    public static void main(String[] args) throws IOException {
        f2();
    }

    /*private static void f1() throws IOException {
        FileReader fileReader = new FileReader(
                "/Users/share/Downloads/JavaProgrammingForBeginners-CourseBook.pdf");

        BufferedReader br = new BufferedReader(fileReader);
        String readLen;
        while ((readLen = br.readLine()) != null) {
            System.out.println(readLen);
        }
    }*/

    private static void f2() throws IOException {
        FileInputStream fs = new FileInputStream(
                "/Users/share/Downloads/JavaProgrammingForBeginners-CourseBook.pdf");

        BufferedInputStream bi = new BufferedInputStream(fs);

        byte[] bytes = new byte[1024];

        while (bi.read(bytes) != -1) {
        }
    }
}
