package java_basic._1109_collection.arrayList.d;



public class Demo {

    public static void main(String[] args) {

        DynamicArray<Object> strArr = new DynamicArray<>();
        strArr.add(0, "luke");
        strArr.add(1, "Jesse");
        strArr.add(2, "Young");
        strArr.add(3, "Steven");
        strArr.add(1, "Mike");
        strArr.addLast(1000);
        strArr.addLast("lastName");
        System.out.printf("strArr values are: %s", strArr.get(6));
    }
}
