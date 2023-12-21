package java_basic._1127_IO.a;

import java.io.File;

public class Main01_ {
    public static void main(String[] args) {

        File file = new File("/Users/share/Desktop/new1.txt");

        /*System.out.println(file.getName());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getAbsoluteFile().exists());
        System.out.println(file.getParent());
        System.out.println(file.length());
        System.out.println(file.exists());
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());*/

        remove01();


    }

    public static void remove() {
        File file = new File("/Users/share/Desktop/new1.txt");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println(file.getPath() + " deleted");
            }
        } else {
            System.out.println("该文件不存在...");
            // file.createNewFile();
        }
    }

    public static void remove01() {

        File file = new File("/Users/share/Desktop/young/a");

        if (file.exists()) {
            if (file.delete()) {
                System.out.println(file.getPath() + " deleted");
            }
        } else {
            System.out.println("该目录不存在...");
            if (file.mkdirs()) {
                System.out.println("创建成功...");
            }else {
                System.out.println("创建失败...");

            }
        }

    }
}
