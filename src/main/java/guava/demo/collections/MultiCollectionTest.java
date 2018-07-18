package guava.demo.collections;

import java.util.Map;

import com.google.common.collect.*;

/**
 * @author xiezhengchao
 * @since 2018/6/10 21:21
 */
public class MultiCollectionTest {

    public static void main(String[] args) {
        multiset();
        multiMap();
        biMap();
    }

    /**
     * 特点:可计数的Set
     */
    private static void multiset() {
        System.out.println(
                "HashMultiset.create() 和Set很像,但是可以统计多少重复的-------------------------------------------------------");
        Multiset<String> multiset = HashMultiset.create();

        multiset.add("aaa");
        multiset.add("aaa");
        multiset.add("aaa");
        multiset.add("bbb");
        multiset.add("bbb");
        multiset.add("ccc");

        // 感觉就是计数用的...
        System.out.println("aaa count:" + multiset.count("aaa"));
        System.out.println("total size:" + multiset.size());
        // 转到set接口,elementSet()
        System.out.println("set size:" + multiset.elementSet().size());
    }

    /**
     * 特点:允许一键多值Map
     */
    private static void multiMap() {
        System.out.println("HashMultimap.create() 值行为类似HashSet-------------------------------------------------------");
        SetMultimap<String, Integer> valueSimilarHashSet = HashMultimap.create();
        valueSimilarHashSet.put("a", 1);
        valueSimilarHashSet.put("a", 1);
        valueSimilarHashSet.put("b", 2);
        // 允许null
        valueSimilarHashSet.put("b", null);
        System.out.println(valueSimilarHashSet);
        System.out.println(valueSimilarHashSet.get("a"));
        System.out.println(valueSimilarHashSet.get("c"));

        System.out.println(
                "ArrayListMultimap.create() 值行为类似ArrayList--------------------------------------------------------");
        ListMultimap<String, Integer> valueSimilarArrayList = ArrayListMultimap.create();
        valueSimilarArrayList.put("a", 1);
        valueSimilarArrayList.put("a", 1);
        valueSimilarArrayList.put("a", 1);
        valueSimilarArrayList.put("b", 2);
        valueSimilarArrayList.put("b", 3);
        valueSimilarArrayList.put("b", 4);
        valueSimilarArrayList.put("c", 10);
        // 允许null
        valueSimilarArrayList.put("c", null);

        System.out.println(valueSimilarArrayList);
        // 不会返回null,最起码都是个空集合
        System.out.println(valueSimilarArrayList.get("d"));
    }

    /**
     * 特点:双向映射,可以翻转
     */
    private static void biMap() {
        System.out
                .println("HashBiMap.create() k,v可以翻转的Map,值不可重复-------------------------------------------------------");
        HashBiMap<String, Integer> biMap = HashBiMap.create();
        biMap.put("a", 1);
        biMap.put("b", 2);
        biMap.put("c", 3);

        System.out.println(biMap);
        Map<Integer, String> inverseMap = biMap.inverse();
        System.out.println(inverseMap);
        // 同一个kv不同的展示方式而已,修改原map还是inverseMap都可以修改原有的值
        biMap.put("d", 4);
        inverseMap.put(5, "e");
        System.out.println(biMap);
        System.out.println(inverseMap);
    }

}
