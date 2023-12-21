package java_basic._1127_IO.b;

import java.io.*;

public class FileReader_ {
    public static void main(String[] args) {

        // readFile();
        writeFile();
    }

    public static void readFile() {


        String filePath = "/Users/share/Desktop/young.txt";
        // String fileOutPath = "/Users/share/Desktop/陈秋阳.jpg";
        FileReader fileReader = null;
        // FileOutputStream fileOutputStream = null;
        int readLength;
        // byte[] buff = new byte[1024 * 10];
        char[] buff = new char[8];
        try {
            fileReader = new FileReader(filePath);
            // fileOutputStream = new FileOutputStream(fileOutPath);
            while ((readLength = fileReader.read()) != -1) {
                System.out.println(readLength);
                System.out.println((char) readLength);
                // fileOutputStream.write(buff);
                // fileOutputStream.write(buff, 0, buff.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                // fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void writeFile() {


        String filePath = "/Users/share/Desktop/young.txt";
        String fileOutPath = "/Users/share/Desktop/young_out.txt";
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        int readLength;
        // byte[] buff = new byte[1024 * 10];
        char[] buff = new char[8];
        try {
            fileReader = new FileReader(filePath);
            fileWriter = new FileWriter(fileOutPath, true);
            while ((readLength = fileReader.read(buff)) != -1) {
                System.out.println(readLength);
                fileWriter.write(buff);
            }
            fileWriter.write("风雨之后，遇见彩虹🌈");
            fileWriter.write("风雨之后，遇见彩虹🌈".toCharArray(), 0, 3);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
