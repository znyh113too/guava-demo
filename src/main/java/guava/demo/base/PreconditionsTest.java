package guava.demo.base;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * @author xiezhengchao
 * @since 2018/5/12 16:55
 */
public class PreconditionsTest {

    private String a;
    private Integer b;

    public PreconditionsTest(String a, Integer b) {
        this.a = checkNotNull(a);
        this.b = checkNotNull(b);
    }

    public static void main(String[] args) {
        String template = "this is template %s is %s good!";
        args = new String[] { "xiaoming", "xiaohuang" };
        String message = format(template, args);

        System.out.println(message);

        checkState(1 == 2);

    }

    /**
     * Preconditions内部的format方法 非常好的替换方法,核心逻辑就是搜索%s然后跳过...
     */
    public static String format(String template, String[] args) {
        StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
        int templateStart = 0;
        int i = 0;
        while (i < args.length) {
            // 获得下一个%s起始位
            int placeholderStart = template.indexOf("%s", templateStart);
            if (placeholderStart == -1) {
                break;
            }
            // 将上一个%s末尾与下一个%s起始位之间的内容写入builder
            builder.append(template, templateStart, placeholderStart);
            // 将参数写入builder
            builder.append(args[i++]);
            // 记录下一个截断起始位
            templateStart = placeholderStart + 2;
        }
        // 将最后一个%s之后的内容追加到builder内
        builder.append(template, templateStart, template.length());
        return builder.toString();
    }

}
