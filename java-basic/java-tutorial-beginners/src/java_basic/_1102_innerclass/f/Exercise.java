package java_basic._1102_innerclass.f;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/3 13:00
 */

public class Exercise {
    public static void main(String[] args) {

        // new Cellphone().alarmClock(()-> System.out.println("懒猪起床啦!!!"));
        Cellphone cellphone = new Cellphone();
        cellphone.alarmClock(new Bell(){
            @Override
            public void ring() {
                System.out.println("懒猪起床啦");
            }
        });
        cellphone.alarmClock(()-> System.out.println("小伙伴上课了"));

    }
}

interface Bell {
    void ring();
}

class Cellphone {

    public void alarmClock(Bell bell) {
        bell.ring();
    }
}
