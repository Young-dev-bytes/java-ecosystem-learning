package java_basic._1103_enum.a;

import java.util.Arrays;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/3 15:32
 */

public class Demo {
    public static void main(String[] args) {
        // System.out.println(Season.SPRING);

        // System.out.println(EnumSeason.SPRING.getDesc() + EnumSeason.SPRING.getNum());
        // System.out.println(EnumSeason.SUMMER.getDesc());

        System.out.println(EnumSeason.SPRINGT.name());
        EnumSeason[] values = EnumSeason.values();
        System.out.println(Arrays.toString(values));
        System.out.println(EnumSeason.valueOf("SpringT"));
    }
}
