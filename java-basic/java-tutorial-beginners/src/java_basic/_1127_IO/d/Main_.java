package java_basic._1127_IO.d;

import java.io.*;

public class Main_ {
    public static void main(String[] args) throws FileNotFoundException {
        // bufferedReader01();
        bufferedReader02();
        // bufferedInputStream01();
        // bufferedWriter();
    }

    public static void bufferedReader01() {
        BufferedReader bufferedReader = null;
        int readLen;
        try {
            bufferedReader = new BufferedReader(new FileReader("/Users/share/Desktop/young.txt"));
            while ((readLen = bufferedReader.read()) != -1) {
                System.out.println(readLen + "-" +(char)readLen);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void bufferedReader02() {
        BufferedReader bufferedReader = null;
        String readLen;
        try {
            bufferedReader = new BufferedReader(new FileReader("/Users/share/Desktop/young.txt"));
            while ((readLen = bufferedReader.readLine()) != null) {
                System.out.println(readLen);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void bufferedInputStream01() {
        BufferedInputStream bufferedInputStream = null;
        int readLen;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream("/Users/share/Desktop/young.txt"));
            InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
            while ((readLen = inputStreamReader.read()) != -1) {
                System.out.print((char) readLen);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void bufferedWriter() {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("/Users/share/Desktop/young_.txt"));
            bufferedWriter.write("陈秋阳");
            bufferedWriter.write("陈秋阳");
            bufferedWriter.newLine();
            bufferedWriter.write("陈秋阳");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
