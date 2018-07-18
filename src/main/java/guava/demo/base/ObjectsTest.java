package guava.demo.base;

import com.google.common.base.MoreObjects;

/**
 * @author xiezhengchao
 * @since 2018/6/2 18:26
 */
public class ObjectsTest {

    private String name;
    private Integer age;

    public static void main(String[] args) throws Exception {
        ObjectsTest objectsTest = new ObjectsTest();
        objectsTest.age = 1;
        objectsTest.name = "aaa";

        // ObjectsTest并没有重写toString方法哦
        System.out.println(objectsTest.toString());

        // 想要调试一个对象属性信息,自定义toString信息可以这么来弄
        System.out.println(MoreObjects.toStringHelper(objectsTest).add("age", objectsTest.age)
                .add("name", objectsTest.name).toString());

        // 一般的非空赋值
        String value = MoreObjects.firstNonNull("first param maybe is null",
                "first param is null,second param will be return");
    }

}
