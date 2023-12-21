package java_basic._1103_enum.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/3 14:59
 */

public class Season {

    public final static Season SPRING = new Season("Spring", "active");
    public final static Season SUMMER = new Season("Summer", "hot");
    public final static Season AUTUMN = new Season("Autumn", "cool");
    public final static Season WINTER = new Season("Winter", "chill");

    private String name;

    private String desc;

    private Season(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
