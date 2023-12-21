package java_basic._1104_wrapper.c;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/6 12:15
 */

public enum Color implements ShowInter {

    RED(255, 0, 0),
    BLUE(0, 0, 255),
    BLACK(0, 0, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0);

    private final int redValue;
    private final int greenValue;
    private final int blueValue;

    Color(int redValue, int greenValue, int blueValue) {
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
    }

    @Override
    public void show() {

        System.out.println("Color{" +
                "redValue=" + redValue +
                ", greenValue=" + greenValue +
                ", blueValue=" + blueValue +
                "} " + super.toString());
    }

    @Override
    public String toString() {
        return "Color{" +
                "redValue=" + redValue +
                ", greenValue=" + greenValue +
                ", blueValue=" + blueValue +
                "} " + super.toString();
    }

    public static void main(String[] args) {

        Color green = Color.GREEN;
        // public final static Color green = new Color("0", 255, 0);
        // Color green =
        green.show();

        switch (green) {
            case RED:
                System.out.println("匹配到红色");
                break;
            case YELLOW:
                System.out.println("匹配到黄色");
                break;
            case BLACK:
                System.out.println("匹配到黑色");
                break;
            case GREEN:
                System.out.println("匹配到绿色");
                break;
            case BLUE:
                System.out.println("匹配到蓝色");
                break;
            default:
                System.out.println("未匹配到任何颜色");
                break;
        }
    }
}
