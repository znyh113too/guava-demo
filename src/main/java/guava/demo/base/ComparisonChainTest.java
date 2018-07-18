package guava.demo.base;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;

/**
 * @author xiezhengchao
 * @since 2018/6/3 00:16
 */
public class ComparisonChainTest implements Comparable<ComparisonChainTest> {

    private Integer age;
    private Integer age2;

    public ComparisonChainTest(Integer age, Integer age2) {
        this.age = age;
        this.age2 = age2;
    }

    public static void main(String[] args) {

        ComparisonChainTest c1 = new ComparisonChainTest(1, 1);
        ComparisonChainTest c2 = new ComparisonChainTest(1, 2);
        ComparisonChainTest c3 = new ComparisonChainTest(2, 5);

        List<ComparisonChainTest> list = Lists.newArrayList(c2, c1, c3);

        System.out.println(list);

        Collections.sort(list);

        System.out.println(list);
    }

    @Override
    public int compareTo(ComparisonChainTest o) {
        // return this.age.compareTo(o.age) ==0?this.age2.compareTo(o.age2):this.age.compareTo(o.age);
        // 这玩意写的太爽了,比以前的自己compareTo写的简单明了多了,而且实现也很优雅,很值得去看看
        return ComparisonChain.start().compare(this.age, o.age).compare(this.age2, o.age2).result();
    }

    @Override
    public String toString() {
        return "c{" + "age=" + age + ", age2=" + age2 + '}';
    }
}
