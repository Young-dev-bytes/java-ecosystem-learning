package java_basic._1106_exception.a;

import java.util.Scanner;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/6 16:43
 */

public class Exercise01 {
    public static void main(String[] args) {

        /*
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        System.out.println("请输入...");

        do {

            String next = scanner.next();
            try {
                Integer i = Integer.parseInt( next);
                flag = false;
            } catch (NumberFormatException e) {
                System.out.println("类型转换失败 " + e.getMessage());
                System.out.println("请重新输入...");
            }catch (Exception e){
                e.printStackTrace();
                // System.out.println("类型转换失败 " + e.getMessage());
            }
        } while (flag);

         */

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入...");
        String next;
        int num;
        while (true) {

            next = scanner.next();
            try {
                num = Integer.parseInt(next);
                System.out.println("成功");
                break;
            } catch (NumberFormatException e) {
                System.out.println("类型转换失败 " + e.getMessage());
                System.out.println("请重新输入...");
            } catch (Exception e) {
                e.printStackTrace();
                // System.out.println("类型转换失败 " + e.getMessage());
            }
        }
        System.out.println("程序结束....");
    }
}
