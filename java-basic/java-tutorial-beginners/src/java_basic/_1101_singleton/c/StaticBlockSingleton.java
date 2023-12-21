package java_basic._1101_singleton.c;

import java.io.File;
import java.io.IOException;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/1 15:38
 */

public class StaticBlockSingleton {

    private StaticBlockSingleton() throws IOException {
        System.out.println("singleton is initializing");
        File.createTempFile("text","2");
    }

    private static StaticBlockSingleton instance;

    static {
        try {
            instance = new StaticBlockSingleton();
        }catch (Exception e){
            System.err.println("failed to create singleton" + e.getMessage());
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}
