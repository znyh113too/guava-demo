package guava.demo.primitives;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;

/**
 * @author xiezhengchao
 * @since 2018/7/9 01:44
 */
public class PrimitivesTest {

    public static void main(String[] args) {

        // 原生类型数组工具,比较可能会用到的是concat方法在将原生数组做连接
        int[] a = new int[] { 1, 2, 3 };
        List<Integer> aa = Arrays.asList(1, 2, 3);
        List<Integer> aac = Ints.asList(1, 2, 3);

        // 将多个byte数组合并起来,内部还是使用System.arraycopy本地方法
        byte[] newBytes = Bytes.concat("aa".getBytes(Charsets.UTF_8), "bb".getBytes(Charsets.UTF_8));
        System.out.println(new String(newBytes, Charsets.UTF_8));

    }
}
