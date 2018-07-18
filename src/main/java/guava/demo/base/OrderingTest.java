package guava.demo.base;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * @author xiezhengchao
 * @since 2018/6/3 23:34
 */
public class OrderingTest {

    private Integer age;
    private Integer age2;

    public OrderingTest(Integer age, Integer age2) {
        this.age = age;
        this.age2 = age2;
    }

    public static void main(String[] args) {

        Ordering<OrderingTest> ordering = Ordering.natural().nullsFirst()
                .onResultOf((Function<OrderingTest, Integer>) input -> input.age).compound(Ordering.natural()
                        .nullsFirst().onResultOf((Function<OrderingTest, Integer>) input -> input.age2));

        List<OrderingTest> list = Lists.newArrayList(new OrderingTest(1, 1), new OrderingTest(1, 1),
                new OrderingTest(null, 1), new OrderingTest(5, 1), new OrderingTest(5, null), new OrderingTest(3, 2),
                new OrderingTest(3, 1), new OrderingTest(1, 2));

        System.out.println(list);

        // list.removeIf(o->o.age==null || o.age2==null);
        list.sort(ordering);

        System.out.println(list);
    }

    @Override
    public String toString() {
        return "o{" + "age=" + age + ", age2=" + age2 + '}';
    }
}
