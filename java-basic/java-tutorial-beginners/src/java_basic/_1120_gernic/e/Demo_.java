package java_basic._1120_gernic.e;


import java.util.*;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/20 19:22
 */

public class Demo_ {

    public static void main(String[] args) {

        Dao<User> dao = new Dao<>();
        dao.save("1001", new User(1001, 10, "user-01"));
        dao.save("1005", new User(1005, 50, "user-05"));
        System.out.println(dao.get("1001"));
        dao.update("1001", new User(1002, 20, "user-02"));
        System.out.println(dao.list());
        dao.delete("1005");
        System.out.println(dao.list());
    }


}

class Dao<T> {

    private Map<String, T> map;

    public Dao() {
        this.map = new HashMap<>();
    }

    public void save(String id, T entity) {
        map.put(id, entity);
    }

    public T get(String id) {
        return map.get(id);
    }

    public void update(String id, T entity) {
        map.put(id, entity);
    }

    public List<T> list() {
        /*List<T> ts = new ArrayList<>();
        for (T value : map.values()) {
            ts.add(value);
        }*/

        /*List<T> ts = new ArrayList<>();
        ts.addAll(map.values());*/
        List<T> ts = new ArrayList<>();
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            ts.add(map.get(key));

        }
        return ts;
    }

    public void delete(String id) {
        map.remove(id);
    }

}


class User {

    private int id;

    private int age;

    private String name;

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
