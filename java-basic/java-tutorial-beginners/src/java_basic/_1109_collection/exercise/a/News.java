package java_basic._1109_collection.exercise.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/19 16:19
 */

public class News {

    private String title;
    private String content;


    public News(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                '}';
    }
}
