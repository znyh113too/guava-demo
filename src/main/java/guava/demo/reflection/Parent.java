package guava.demo.reflection;

import com.google.common.collect.ComparisonChain;
import com.google.common.reflect.TypeToken;

/**
 * @author xiezhengchao
 * @since 2018/7/18 01:32
 */
public class Parent<T> implements Comparable<Parent> {

    /** 这种写法可以获取泛型类型,这样在顶层可以做一些动作 */
    TypeToken<T> type = new TypeToken<T>(getClass()) {
    };

    private int age;

    public Parent(int age) {
        this.age = age;
    }

    public void getGenericityType() {
        System.out.println("getGenericityType:type.getRawType():" + type.getRawType());
    }

    @Override
    public int compareTo(Parent o) {

        return ComparisonChain.start().compare(this.age, o.age).result();
    }
}
