public class Exercise_01 {

    public static void main(String[] args) {

        short s = 12;
//        System.out.println(s);

        /**
         * char => int 自动转换
        int a = 'c';
        System.out.println(a);
         */


        /**
         * int => double 自动转换
        double b = 80;
        System.out.println(b);
         */


        /**
         * double => float 强转()
        int n1 = 10;
        float d1 = (float) (n1 + 1.1);
        System.out.println(d1);

        byte b2 = 20;
        char c1 = (char) b2;
         */
        byte b2 = 10;
        byte b3 = 20;
        byte b4 = (byte) (b2 + b3);

        /**
         * ternary operator
        int a = 98;
        int b = 21;
        int c = 9;
        int max = a > b ? a > c ? a : c : b > c ? b : c;
        System.out.println(max);
         */


        String b = "123";

        System.out.println(b = "0000");




    }

}
