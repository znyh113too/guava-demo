package guava.demo.range;

import com.google.common.collect.Range;

/**
 * @author xiezhengchao
 * @since 2018/7/16 02:23
 */
public class RangeTest {

    public static void main(String[] args) {

        // 开区间(a,b)
        System.out.println(Range.open(1, 3).contains(1));

        // 闭区间[a,b]
        System.out.println(Range.closed(1, 3).contains(1));

        // (-∞..b)
        System.out.println(Range.lessThan(1).contains(1));
        // (a..+∞)
        System.out.println(Range.greaterThan(1).contains(1));
        // [a..+∞)
        System.out.println(Range.atLeast(1).contains(1));
        // (-∞..b]
        System.out.println(Range.atMost(1).contains(1));

    }
}
