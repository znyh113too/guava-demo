package guava.demo.event;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * @author xiezhengchao
 * @since 2018/7/17 01:06
 */
public class EventBusTest {

    public static void main(String[] args) throws InterruptedException {

        // 1拥有EventBus实例
        EventBus eventBus = new EventBus();

        // 2注册监听器
        eventBus.register(new EventBusTest());

        // 3发送消息(一般1和2在一起配置,3分布在代码各处)
        eventBus.post(new CustomEvent("zhang san"));

        // 异步的EventBus
        EventBus asyncEventBus = new AsyncEventBus("AsyncEventBus",
                new ThreadPoolExecutor(1, 5, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadFactory() {
                    private AtomicInteger atomicInteger = new AtomicInteger(0);

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setName("AsyncEventBusExecutorService.Pool-" + atomicInteger.getAndIncrement());
                        return t;
                    }
                }));

        asyncEventBus.register(new EventBusTest());
        asyncEventBus.post(new CustomEvent("li si"));

        asyncEventBus.post(new CustomEvent2());

        // 正常退出
        TimeUnit.SECONDS.sleep(1);
        System.exit(0);
    }

    /**
     * 定义方法接收事件
     */
    @Subscribe
    public void eventReceiver(CustomEvent changeEvent) {
        System.out.println("Thread name:" + Thread.currentThread().getName() + " receive event:" + changeEvent);

    }

    /**
     * 可以定义多个,符合继承体也可以获得消息,均会被触发
     */
    // @Subscribe
    // public void eventReceiver(Object event) {
    // System.out.println("Thread name:"+Thread.currentThread().getName()+" receive object:" + event);
    // }

    /**
     * 如果都没有被触发,那么会尝试给DeadEvent事件,如果没有定义这个事件,那么就当做什么都没发生
     */
    @Subscribe
    public void deadEvent(DeadEvent event) {
        System.out.println("Thread name:" + Thread.currentThread().getName() + " deadEvent:" + event);
    }

    /**
     * 事件消息体
     */
    private static class CustomEvent {
        private String name;

        public CustomEvent(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "CustomEvent{" + "name='" + name + '\'' + '}';
        }
    }

    private static class CustomEvent2 {
        @Override
        public String toString() {
            return "CustomEvent2{}";
        }
    }
}
