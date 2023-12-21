package java_basic._1022_circul;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/22 14:18
 */

public class Exercise_04 {
    public static void main(String[] args) {

        /**
         * 某人有100000元，每经过一次路口，需要交费，规则如下：
         * 当现金 > 50000时，每次交5%
         * 当现金 <= 50000时，每次交1000
         */
        int money = 100000;
        int num = 0;

        while (true){
            if(money > 50000){
                // money = (int) (money - money * 0.05);
                money *= 0.95;
                num++;
            }else if(money >= 1000){
                money -= 1000;
                num++;
            }else {
                break;
            }
        }
        System.out.println(money);
        System.out.println(num);
    }
}
