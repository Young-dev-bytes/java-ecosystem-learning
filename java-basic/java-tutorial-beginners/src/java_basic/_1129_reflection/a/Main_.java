package java_basic._1129_reflection.a;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Properties;

public class Main_ {
    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src/re.properties"));
        String classfullpath = properties.get("classfullpath").toString();
        String methodName = properties.get("method").toString();

        // System.out.println(classfullpath);
        // System.out.println(method);

        Class<?> cls = Class.forName(classfullpath);

        System.out.println(cls.getName());
        System.out.println(Arrays.toString(cls.getFields()));
        System.out.println((Arrays.toString(cls.getMethods())));

        Object o = cls.newInstance();
        System.out.println(o.getClass());
        System.out.println(cls.getMethod(methodName));
        System.out.println(cls.getMethod(methodName).getName());
        System.out.println(Arrays.toString(cls.getMethod(methodName, String.class, Integer.class).getTypeParameters()));
        cls.getMethod(methodName).invoke(o);
        System.out.println(cls.getField("name").get(o));
        Constructor<?> constructor = cls.getConstructor();
        Class<Integer> integerClass = int.class;
        System.out.println("integerClass: " + integerClass);
        Constructor<?> constructor1 = cls.getConstructor(String.class, Integer.class);
        System.out.println(constructor);
        System.out.println(constructor1);
        Object o1 = constructor1.newInstance("小白", 200);
        System.out.println(o1);

        Class<Integer> type = Integer.TYPE;
        System.out.println(type.hashCode());
        System.out.println(integerClass.hashCode());
        Class<Void> voidClass = void.class;
    }


}
