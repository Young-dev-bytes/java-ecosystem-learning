package java_basic._1109_collection.exercise.a;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/19 16:21
 */

@SuppressWarnings("all")
public class Demo {

    public static void main(String[] args) {

        News news1 = new News("新冠确诊病例超千万");
        News news2 = new News("男子突然想起两个月钱");

        List newsList = new ArrayList();
        newsList.add(news1);
        newsList.add(news2);

        int size = newsList.size();
        for (int i = size - 1; i >= 0; i--) {
            News news = (News)newsList.get(i);

        }
        System.out.println(newsList);


    }
}
