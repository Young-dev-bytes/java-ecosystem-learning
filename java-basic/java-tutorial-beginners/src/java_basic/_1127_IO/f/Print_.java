package java_basic._1127_IO.f;

import java.io.*;
import java.util.Properties;

public class Print_ {
    public static void main(String[] args) throws IOException {


        /*PrintStream out = System.out;
        out.print("1123");
        out.write("陈秋阳".getBytes());
        System.out.println();

        System.setOut(new PrintStream(""));
        out.close();*/

        // PrintWriter printWriter = new PrintWriter(System.out);
        /*PrintWriter printWriter = new PrintWriter("/Users/share/Desktop/aa.txt");
        printWriter.print("hi, 你好");
        printWriter.flush();*/

        BufferedReader br = new BufferedReader(new FileReader("src/sql.properties"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/sql2.properties"));
        // BufferedWriter bw = new BufferedWriter(new Fi("src/sql2.properties"));
        /*String line = "";
        while ((line = br.readLine()) != null){
            System.out.println(line);
        }*/

        Properties properties = new Properties();
        properties.load(br);
        System.out.println(properties.getProperty("password"));
        System.out.println(properties.getProperty("username"));
        properties.setProperty("username","陈秋阳");

        properties.list(System.out);
        PrintWriter out = new PrintWriter("src/sql1.properties");
        properties.list(out);
        out.close();

        properties.store(bw,"comment");
        properties.store(new FileOutputStream("src/sql3.properties"),null);




    }
}
