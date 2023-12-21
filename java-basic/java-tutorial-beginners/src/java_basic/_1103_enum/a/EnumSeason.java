package java_basic._1103_enum.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/3 14:59
 */

public enum EnumSeason {

    SPRINGT ("Spring", "active", 0),
    SUMMER,
    AUTUMN ("Autumn", "cool",1),
    WINTER ("Winter", "chill",2);

    private String nameT;

    private String desc;

    private int num;

    private EnumSeason(){

    }


    private EnumSeason(String nameT, String desc, int num) {
        this.nameT = nameT;
        this.desc = desc;
        this.num = num;
    }

    public String getName() {
        return nameT;
    }

    public String getDesc() {
        return desc;
    }

    public int getNum() {
        return num;
    }

//    @Override
//    public String toString() {
//        return "EnumSeason{" +
//                "nameT='" + nameT + '\'' +
//                ", desc='" + desc + '\'' +
//                '}';
//    }
}

