package java_basic._1127_IO.d;

import java.io.*;

public class Buffer_ {
    public static void main(String[] args) {
        BufferedCopy_();
    }

    public static void BufferedCopy() {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        String readLen;
        try {
            bufferedReader = new BufferedReader(new FileReader("/Users/share/Desktop/Screen Recording 2023-11-23 at 14.52.43.mov"));
            bufferedWriter = new BufferedWriter(new FileWriter("/Users/share/Desktop/Screen Recording_young.mov"));
            while ((readLen = bufferedReader.readLine()) != null) {
                // System.out.println((char) readLen);
                bufferedWriter.write(readLen);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void BufferedCopy_() {
        BufferedInputStream bufferedReader = null;
        BufferedOutputStream bufferedWriter = null;
        // ObjectOutputStream
        int readLen;
        byte[] bytes = new byte[1028 * 10];
        try {
            bufferedReader = new BufferedInputStream(new FileInputStream("/Users/share/Desktop/Screen Recording 2023-11-23 at 14.52.43.mov"));
            bufferedWriter = new BufferedOutputStream(new FileOutputStream("/Users/share/Desktop/Screen Recording 2023-11-23 at 14.52.43_young.mov"));
            while ((readLen = bufferedReader.read(bytes)) != -1) {
                // System.out.println((char) readLen);
                bufferedWriter.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
