package design_patterns.a_SOLIDDesignPrinciples.a_SRP;

/**
 * @author Young.
 * @version 1.0
 *
 * @description:
 * 单一职责原则（Single Responsibility Principle）：
 * 一个类应该只有一个引起变化的原因，即一个类应该只负责一项职责
 *
 * @date 2023/11/3 17:57
 */

import java.awt.*;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class Journal {

    private final List<String> entries = new ArrayList<>();

    private static int count = 0;

    public void addEntry(String text) {
        entries.add("🤔" + (++count) + ": " + text);
    }

    public void removeEntry(int index) {
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }

    // here we break SRP
    public void save(String filename) throws Exception {
        try (PrintStream out = new PrintStream(filename)) {
            out.println(toString());
        }
    }

    public void load(String filename) {
    }

    public void load(URL url) {
    }
}

// handles the responsibility of persisting objects
class Persistence {
    public void saveToFile(Journal journal,
                           String filename, boolean overwrite) throws Exception {

        if (overwrite || new File(filename).exists())
            try (PrintStream out = new PrintStream(filename)) {
                out.println(journal.toString());
            }
    }

    public void load(Journal journal, String filename) {
    }

    public void load(Journal journal, URL url) {
    }
}

class SRPDemo {
    public static void main(String[] args) throws Exception {
        Journal j = new Journal();
        j.addEntry("I cried today");
        j.addEntry("I ate a bug");
        System.out.println(j);

        Persistence p = new Persistence();
        String filename = "/Users/share/Documents/studyfolders/backend/java/java-tutorial-beginners/src/design_patterns/SOLIDDesignPrinciples/journal.txt";
        p.saveToFile(j, filename, true);

        // windows!
        // Runtime.getRuntime().exec("notepad.exe " + filename);
        // /Users/share/Documents/studyfolders/backend/java/java-tutorial-beginners/src/design_patterns

        Desktop desktop = Desktop.getDesktop();


    }
}
