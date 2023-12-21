package java_basic._1022_circul;

import java.util.Scanner;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/22 14:18
 */

public class MulForDemo {
    public static void main(String[] args) {

        double totalScore = 0;
        int classCount = 2;
        int count = 3;
        int passNum = 0;
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= classCount; i++) {
            double sum = 0;
            double average = 0;
            for (int j = 1; j <= count; j++) {
                System.out.println("请输入第"+i+"个班的第" + j + "个学生的成绩");
                double score = scanner.nextDouble();
                if(score >= 60){
                    passNum++;
                }
                sum += score;
            }

            average =  sum / count;
            System.out.println("第"+i+"个班的总分是：" + sum);
            System.out.println("第"+i+"个班的平均分是：" + average);
            totalScore += sum;
        }

        System.out.println("所有班级的平均分为" + totalScore/(classCount*count));
        System.out.println("及格人数为：" + passNum);
    }
}
