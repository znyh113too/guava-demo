package guava.demo.hash;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * @author xiezhengchao
 * @since 2018/7/16 02:34
 */
public class HashTest {

    public static void main(String[] args) throws Exception {

        // 构造散列算法
        HashFunction hf = Hashing.md5();
        // 多种多样
        // Hashing.hmacMd5();
        // Hashing.crc32();
        // Hashing.sha256();

        // 获取HashCode
        HashCode hc = hf.newHasher().putBytes("your origin bytes".getBytes(StandardCharsets.UTF_8)).hash();
        String md5Str = getMD5FromJdk("your origin bytes");

        // 多种多样的比较方法
        System.out.println("hc.toString():" + hc.toString());
        System.out.println("md5Str:" + md5Str);
        System.out.println(hc.toString().equals(md5Str));
        // hc.asBytes()
        // hc.asInt()
    }

    private static String getMD5FromJdk(String str) throws Exception {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new Exception("MD5加密出现错误，" + e.toString());
        }
    }
}
