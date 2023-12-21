package java_basic._1127_IO.f;

import java.io.*;

public class Main_ {


    public static void main(String[] args) {
        // f1();
        f2();
    }


    public static void f1() {
        BufferedReader bufferedReader = null;
        String readLen;
        try {
            bufferedReader = new BufferedReader(new FileReader("/Users/share/Desktop/young.txt"));
            while ((readLen = bufferedReader.readLine()) != null) {
                System.out.println(readLen);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void f2() {
        BufferedReader br = null;
        OutputStreamWriter osw = null;
        String fileInPath = "/Users/share/Desktop/young.txt";

        try {
            // 把 InputStreamReader 传入到 BufferedReader,
            // 因为BufferedReader读取的更快一点，效率更高一点
            // 其实 InputStreamReader 也能读只是功能太单一，方法少，效率低
            br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileInPath), "utf8"));
            osw = new OutputStreamWriter(new FileOutputStream("/Users/share/Desktop/young___.txt"), "utf8");
            String readLen;
            // char[] chars = new char[8];
            while ((readLen = br.readLine()) != null) {
                System.out.println(readLen);
                osw.write(readLen);
            }
            /*while ((readLen = isr.read(chars)) != -1) {
                // System.out.println((char) readLen);
                System.out.println(new String(chars).substring(0, readLen));
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭外层流即可
                br.close();
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}


