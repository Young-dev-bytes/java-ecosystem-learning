package java_basic._1103_enum.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/3 16:37
 */

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        System.out.println(EnumSeason.SPRING.name());
        System.out.println(EnumSeason.SUMMER.name());
        System.out.println(EnumSeason.SPRING.ordinal());
        System.out.println(EnumSeason.WINTER.toString());
        System.out.println(EnumSeason.AUTUMN.equals(EnumSeason.SUMMER));
        System.out.println(EnumSeason.AUTUMN.hashCode());
        System.out.println(EnumSeason.SPRING.compareTo(EnumSeason.WINTER));
        System.out.println(EnumSeason.SPRING.getDeclaringClass().newInstance());


    }
}
