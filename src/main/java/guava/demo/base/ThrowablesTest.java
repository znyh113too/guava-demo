package guava.demo.base;

import com.google.common.base.Throwables;

/**
 * @author xiezhengchao
 * @since 2018/6/4 01:09
 */
public class ThrowablesTest {

    public static void main(String[] args) {
        Throwable t1 = new RuntimeException("fake");
        Throwable t2 = new RuntimeException("fake2", t1);

        // 异常堆栈信息到字符串,这个还有点用
        System.out.println(Throwables.getStackTraceAsString(t2));
        System.out.println(Throwables.getStackTraceAsString(t2));
        System.out.println("------------------------------------");

        // System.out.println(Throwables.getCausalChain(t2));
        // System.out.println("------------------------------------");
        //
        // Throwables.getRootCause(t2).printStackTrace();
    }
}
