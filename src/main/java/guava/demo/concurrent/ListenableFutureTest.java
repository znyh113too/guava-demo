package guava.demo.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.google.common.util.concurrent.*;

/**
 * @author xiezhengchao
 * @since 2018/6/25 01:16
 */
public class ListenableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService =
                new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadFactory() {
                    private AtomicInteger atomicInteger = new AtomicInteger(0);

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setName("ListeningExecutorService.Pool-" + atomicInteger.getAndIncrement());
                        return t;
                    }
                });

        // 包装得到ListeningExecutorService
        ListeningExecutorService les = MoreExecutors.listeningDecorator(executorService);

        ListenableFuture<String> listenableFuture = les.submit(() -> {
            TimeUnit.SECONDS.sleep(1);
            return "aaa";
        });

        // 普通的使用监听
        listenableFuture.addListener(() -> {
            try {
                System.out.println("listenableFuture.addListener..success:" + listenableFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }, executorService);

        // 通过Futures工具类使用监听
        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("Futures.addCallback success..." + result);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        }, executorService);

        System.out.println("--------------------------------------------");
        System.out.println("正常处理返回值:" + listenableFuture.get());
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

        // 对ListenableFuture进行合并操作,这个还比较有用
        List<ListenableFuture<String>> listenableFutureList = new ArrayList<>();
        listenableFutureList.add(les.submit(() -> "aaa"));
        listenableFutureList.add(les.submit(() -> "bbb"));
        listenableFutureList.add(les.submit(() -> {
            throw new RuntimeException("runtime exception");
        }));
        listenableFutureList.add(les.submit(() -> "ccc"));

        // successfulAsList 有异常则返回null
        // allAsList 有一个有异常都会走failure
        ListenableFuture<List<String>> listListenableFuture = Futures.successfulAsList(listenableFutureList);
        // ListenableFuture<List<String>> listListenableFuture = Futures.allAsList(listenableFutureList);
        Futures.addCallback(listListenableFuture, new FutureCallback<List<String>>() {
            @Override
            public void onSuccess(@Nullable List<String> result) {
                System.out.println("Futures.successfulAsList result:" + result);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        }, executorService);

        System.out.println("listListenableFuture.get():" + listListenableFuture.get());

        TimeUnit.SECONDS.sleep(1);
        System.exit(0);
    }
}
