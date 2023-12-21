package java_basic._1030_debug.c;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/30 15:44
 */

public class SmallChangeSys {
    public static void main(String[] args) {
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        String key = "";

        do {
            System.out.println("\n===========零钱通菜单===========");
            System.out.println("\t\t\t1 零钱通明细");
            System.out.println("\t\t\t2 收益入账");
            System.out.println("\t\t\t3 消费");
            System.out.println("\t\t\t4 退         出");

            System.out.println("请选择（1-4）： ");
            key = scanner.next();

            String details = "===========零钱通明细===========";
            double money = 0;
            double balance = 0;
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");



            switch (key) {
                case "1":
                    System.out.println(details);
                    break;
                case "2":
                    System.out.println("收益入账金额：");
                    money = scanner.nextDouble();
                    balance += money;
                    date = new Date();

                    details += "\n收益入账\t+" + money + "\t" + sdf.format(date) + "\t余额" + balance;

                    break;
                case "3":
                    System.out.println("3 消费");
                    break;
                case "4":
                    System.out.println("4 退         出");
                    loop = false;
                    break;
                default:
                    System.out.println("菜单输入有误，请重新选择菜单...");
            }
        } while (loop);

        System.out.println("===========退出菜单===========");
    }
}
