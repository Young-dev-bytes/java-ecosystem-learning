package java_basic._1127_IO.b;

import java.io.*;

public class Main_ {
    public static void main(String[] args) {
        readFile01();
        // readFile02();
        // readFile01_();
    }


    public static void readFile01() {
        String filePath = "/Users/share/Desktop/new2.txt";
        FileInputStream fileInputStream = null;
        int readData = 0;
        // byte[] buff = new byte[1];

        try {
            // 创建FileInputStream对象, 用于读取文件
            fileInputStream = new FileInputStream(filePath);
            // int read = fileInputStream.read(buff);
            // System.out.println(read);
            // System.out.println(new String(buff));
            // 从该输入流中读取一个字节的数据, 如果没有输入可用此方法将阻止
            // 如果返回-1，表示读取完毕
            while ((readData = fileInputStream.read()) != -1) {
                System.out.print((char) readData + " - " + readData);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readFile01_() {
        String filePath = "/Users/share/Desktop/new2.txt";
        FileInputStream fileInputStream = null;
        byte[] buf = new byte[6];
        int readLength = 0;
        try {
            // 创建FileInputStream对象, 用于读取文件
            fileInputStream = new FileInputStream(filePath);
            // 从该输入流中读取最多buf.length字节的数据到字节数组上，此方法将阻塞，直到某些输入可用
            // 如果返回-1，表示读取完毕
            // 如果读取正常，则返回实际读取的字节数
            while ((readLength = fileInputStream.read(buf)) != -1) {
                System.out.println(readLength);
                String x = new String(buf, 0, readLength);
                System.out.println(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readFile02() {
        String filePath = "/Users/share/Desktop/new2.txt";
        FileReader fileReader = null;
        int readData = 0;
        try {
            fileReader = new FileReader(filePath);
            while ((readData = fileReader.read()) != -1) {
                System.out.println((char) readData + " - " + readData);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
