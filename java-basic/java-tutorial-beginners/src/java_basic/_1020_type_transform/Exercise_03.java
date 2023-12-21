package java_basic._1020_type_transform;

public class Exercise_03 {

    public static void main(String[] args) {


        /*
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入a-e：");
        char x = scanner.next().charAt(0);
        switch (x){
            case 'a':
                System.out.println((char)('a' - 32));
                break;
            case 'b':
                System.out.println((char)('b' - 32));
                break;
            case 'c':
                System.out.println((char)('c' - 32));
                break;
            case 'd':
                System.out.println((char)('d' - 32));
                break;
            case 'e':
                System.out.println((char)('e' - 32));
                break;
            default:
                System.out.println("other");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入学生成绩：");
        double score = scanner.nextDouble();
        if(score >= 0 && score <=100){
            switch ((int)(score/60)){
                case 0:
                    System.out.println("不合格");
                    break;
                case 1:
                    System.out.println("合格");
                    break;
                default:
                    System.out.println("输入有误");
                    break;
            }
        }else {
            System.out.println("成绩输入有误");
        }
         */

        switch (3){
            case 3:
            case 4:
            case 5:
                System.out.println("春季");
                break;
            case 6:
                System.out.println("夏季");
            case 7:
                System.out.println("夏季");
            case 8:
                System.out.println("夏季");
                break;
            case 9:
                System.out.println("秋季");
            case 10:
                System.out.println("秋季");
            case 11:
                System.out.println("秋季");
            default:
                System.out.println("other");
        }

    }



}
