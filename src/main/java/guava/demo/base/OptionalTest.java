package guava.demo.base;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Optional;

/**
 * @author xiezhengchao
 * @since 2018/5/12 16:51
 */
public class OptionalTest {

    /**
     * 防御式编程 原则:如果返回的值存在null的可能尽量用Optional,这样使用方就必须检查.jdk已经提供了支持
     */
    public static void main(String[] args) {
        String a = null;
        String c = Optional.ofNullable(a).orElse("b");
        System.out.println(c);
        String d = Optional.ofNullable(a).orElseThrow(() -> new RuntimeException("not null!"));
        System.out.println(d);

        String ccc = checkNotNull(a) + "fake";
    }

}
