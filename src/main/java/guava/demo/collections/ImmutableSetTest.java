package guava.demo.collections;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

/**
 * @author xiezhengchao
 * @since 2018/6/4 02:15
 */
public class ImmutableSetTest {

    public static void main(String[] args) {
        Set<String> immutableSet = ImmutableSet.of("a", "b", "c");

        Set<String> set = new HashSet<String>() {
            {
                add("1");
                add("2");
                add("3");
            }
        };
        set.add("4");
        // 不可变对象不能含有null
        // set.add(null);

        Set<Integer> builderSet = ImmutableSet.<Integer> builder().add(1).add(2).add(3).build();

        Set<String> iSet = ImmutableSet.copyOf(set);

        System.out.println(iSet);
        System.out.println(immutableSet);
        System.out.println(builderSet);
    }
}
