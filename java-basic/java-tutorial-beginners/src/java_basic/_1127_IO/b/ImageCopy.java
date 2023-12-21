package java_basic._1127_IO.b;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageCopy {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        imageCopy();
        long end = System.currentTimeMillis();
        System.out.println("\n" + (end-start));


    }

    public static void imageCopy() {

        // String filePath = "/Users/share/Desktop/陈秋阳.jpg";
        String filePath = "/Users/share/Desktop/Screen Recording 2023-11-23 at 14.52.43.mov";
        String fileOutPath = "/Users/share/Desktop/Screen Recording 2023-11-23 at 14.52.43_copy.mov";
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        int readLength;
        byte[] buff = new byte[1024 * 10];
        try {
            fileInputStream = new FileInputStream(filePath);
            fileOutputStream = new FileOutputStream(fileOutPath);
            while ((readLength = fileInputStream.read(buff)) != -1){
                // System.out.println(readLength);
                System.out.print(new String(buff));
                fileOutputStream.write(buff);
                fileOutputStream.write(buff, 0, buff.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


