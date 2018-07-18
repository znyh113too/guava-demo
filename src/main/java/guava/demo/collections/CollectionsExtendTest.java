package guava.demo.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.*;

/**
 * @author xiezhengchao
 * @since 2018/6/19 01:19
 */
public class CollectionsExtendTest {

    public static void main(String[] args) {
        forwarding();
        peekingIterator();
        abstractIterator();
    }

    private static void forwarding() {
        System.out.println(
                "forwarding ------------------------------------------------------------------------------------");

        // 快速创建包装类拓展
        List<String> list = new ForwardingList<String>() {
            private final List<String> delegate = new ArrayList<>();

            @Override
            protected List<String> delegate() {
                System.out.println("delegate....");
                return delegate;
            }

            @Override
            public boolean add(String element) {
                System.out.println("add..." + element);
                return super.add(element);
            }
        };

        list.add("1");
        list.add("2");
        System.out.println(list);
    }

    private static void peekingIterator() {
        System.out.println(
                "peekingIterator ------------------------------------------------------------------------------------");

        // 可以进行窥探下一个值的迭代器
        PeekingIterator<String> peekingIterator =
                Iterators.peekingIterator(Lists.newArrayList("a", "b", "b", "c", "c").iterator());

        List<String> result = new ArrayList<>();
        while (peekingIterator.hasNext()) {
            String current = peekingIterator.next();
            while (peekingIterator.hasNext() && peekingIterator.peek().equals(current)) {
                peekingIterator.next();
            }
            result.add(current);
        }
        System.out.println(result);
    }

    private static void abstractIterator() {
        System.out.println(
                "abstractIterator ------------------------------------------------------------------------------------");

        // 自定义迭代器
        Iterator<String> in = Iterators.forArray("a", null, "b", "c");
        Iterator<String> iterator = new AbstractIterator<String>() {
            @Override
            protected String computeNext() {
                while (in.hasNext()) {
                    String s = in.next();
                    if (s != null) {
                        return s;
                    }
                }
                return endOfData();
            }
        };
        iterator.forEachRemaining(System.out::print);
    }
}
