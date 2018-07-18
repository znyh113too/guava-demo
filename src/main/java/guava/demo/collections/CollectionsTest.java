package guava.demo.collections;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.google.common.collect.*;
import com.google.common.primitives.Ints;

/**
 * @author xiezhengchao
 * @since 2018/6/11 00:20
 */
public class CollectionsTest {

    public static void main(String[] args) {
        lists();
        sets();
        maps();
        multiMaps();
    }

    private static void lists() {
        System.out.println("Lists ----------------------------------------------------------------------");
        // 一般没有参数的情况我是不会用这个方法的
        Lists.newArrayList();

        // 用的较多
        List<Integer> lists = Lists.newArrayList(1, 2, 3, 4);
        // 反转
        System.out.println(Lists.reverse(lists));

        // 指定初始化大小的list
        Lists.newArrayListWithCapacity(3);
    }

    private static void sets() {
        System.out.println("Sets ----------------------------------------------------------------------");
        // 和Lists一样的构造方法
        Sets.newHashSet("", "");

        // 偶尔会用到,比较引用
        Sets.newIdentityHashSet();

        Set<String> a = ImmutableSet.of("a", "b", "c", "d");
        Set<String> b = ImmutableSet.of("a", "b", "e", "f");

        TreeSet<String> treeSet = new TreeSet<>(Ordering.natural());

        // 方便的对set进行比较
        System.out.println("union:" + Sets.union(a, b));
        System.out.println("intersection:" + Sets.intersection(a, b));
        System.out.println("difference:" + Sets.difference(a, b));
        System.out.println("symmetricDifference:" + Sets.symmetricDifference(a, b));
    }

    private static void maps() {
        System.out.println("Maps ----------------------------------------------------------------------");
        Maps.newHashMap();

        // 分组
        Map<Integer, String> uniqueIndexMap =
                Maps.uniqueIndex(ImmutableList.copyOf(new String[] { "a", "ab", "abc" }), String::length);
        System.out.println(uniqueIndexMap);

        Map<String, Integer> map1 = ImmutableMap.of("a", 1, "b", 2, "c", 3, "e", 5);
        Map<String, Integer> map2 = ImmutableMap.of("a", 1, "b", 2, "c", 4, "d", 4);

        // 2个map进行比较
        MapDifference<String, Integer> mapDifference = Maps.difference(map1, map2);
        System.out.println("entriesInCommon:" + mapDifference.entriesInCommon());
        System.out.println("entriesDiffering:" + mapDifference.entriesDiffering());
        System.out.println("entriesOnlyOnLeft:" + mapDifference.entriesOnlyOnLeft());
        System.out.println("entriesOnlyOnRight:" + mapDifference.entriesOnlyOnRight());
    }

    private static void multiMaps() {
        System.out.println("multiMaps ----------------------------------------------------------------------");

        Set<String> set = ImmutableSet.of("a", "b", "ab", "abc", "add");
        System.out.println(Multimaps.index(set, String::length));

        ArrayListMultimap<String, Integer> arrayListMultimap = ArrayListMultimap.create();
        arrayListMultimap.putAll("a", Ints.asList(1, 2, 3));
        arrayListMultimap.putAll("b", Ints.asList(4, 5, 3));
        arrayListMultimap.putAll("a", Ints.asList(5, 6, 7));

        TreeMultimap<Integer, String> treeMultimap = Multimaps.invertFrom(arrayListMultimap, TreeMultimap.create());
        System.out.println(treeMultimap);
    }
}
