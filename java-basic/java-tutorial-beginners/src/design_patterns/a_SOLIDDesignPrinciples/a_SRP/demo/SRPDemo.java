package design_patterns.a_SOLIDDesignPrinciples.a_SRP.demo;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/6 18:07
 */


class Journal implements Serializable {


    private final List<String> entries = new ArrayList<>();

    private static int count = 0;

    public void addEntries(String journalName) {
        entries.add("🍕 " + (++count) + " - " + journalName);
    }

    public void removeEntries(int index) {
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }
}

class Persistence {

    public static void saveToFile(Journal journal, String filename) {

        /*PrintStream out = null;
        try {
            out = new PrintStream(filename);
            out.println(journal.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            assert out != null;
            out.close();
        }*/

        /*try(PrintStream out = new PrintStream(filename)){
            out.println(journal.toString());
        }catch (FileNotFoundException e){
            throw new Exception("文件找不到");
        }*/

        try (PrintStream out = new PrintStream(filename)) {
            out.println(journal.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("写入文件失败: " + e.getMessage());
            //throw new RuntimeException("写入文件异常");
        }

        System.out.println("程序继承执行-1....");


    }

}

public class SRPDemo {

    public static void main(String[] args) {
        Journal journal = new Journal();
        journal.addEntries("Young");
        journal.addEntries("list");
        // journal.removeEntries(0);
        System.out.println(journal);

        String filename = "/Users/share/Documents/studyfolders/backend/java/java-tutorial-beginners/src/design_patterns/_SOLIDDesignPrinciples/journal.txt";

        try {
            Persistence.saveToFile(journal, filename);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败....");
        }

        System.out.println("程序继承执行-2....");

        Desktop desktop = Desktop.getDesktop();
        File file = new File(filename);

        if (file.exists()) {
            try {
                desktop.open(file);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("打开文件失败，异常消息是 :  " + e.getMessage());
            }
        }
    }


}
