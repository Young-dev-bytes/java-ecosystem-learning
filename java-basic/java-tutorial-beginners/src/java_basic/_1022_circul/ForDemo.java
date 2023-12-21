package java_basic._1022_circul;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/22 14:18
 */

public class ForDemo {
    public static void main(String[] args) {


//        int i = 0;
//        for (; i <= 10;  ){
//            System.out.println(i);
//            i++;
//        }

//        // 使用前缀递增运算符
//        for (int i = 0; i < 5; ) {
//            System.out.println("Current value using prefix: " + ++i);
//        }
//
//        // 使用后缀递增运算符
//        for (int i = 0; i < 5; ) {
//            System.out.println("Current value using postfix: " + i++);
//        }
        int count = 3;
        for(int i = 0, j = 0; i < count; i++, j+=2){
            System.out.println("i=" + i + "j=" + j);
        }


    }
}
