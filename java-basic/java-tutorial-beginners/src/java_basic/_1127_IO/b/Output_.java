package java_basic._1127_IO.b;

import java.io.FileOutputStream;
import java.io.IOException;

public class Output_ {
    public static void main(String[] args) {
        writeFile();

    }

    public static void writeFile() {

        String filePath = "/Users/share/Desktop/young.txt";
        FileOutputStream fileOutputStream = null;
        try {
            // fileOutputStream = new FileOutputStream(filePath, true);
            fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(98);
            fileOutputStream.write(new byte[]{'a', 'b', 103});
            fileOutputStream.write('g');
            fileOutputStream.write("hello".getBytes(), 0, 4);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
