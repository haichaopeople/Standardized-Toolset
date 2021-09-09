package morn.library.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程执行工具类
 */
@Slf4j
public class ThreadUtil {
    private static volatile ExecutorService executor;

    static {
        initExecutor();
    }

    /**
     * 异步执行一个任务
     *
     * @param task 任务
     * @return 任务结果
     */
    public static <V> Future<V> submit(Callable<V> task) {
        return executor.submit(task);
    }

    /**
     * 异步执行，超时会停止任务
     *
     * @param task    任务
     * @param timeout 超时时间为秒
     * @return 任务结果
     */
    public static <T> T submit(Callable<T> task, int timeout) {
        Future<T> future = executor.submit(task);
        T t;
        try {
            t = future.get(timeout, TimeUnit.SECONDS);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            future.cancel(true);
        }
        return null;
    }

    /**
     * 异步执行一个任务
     *
     * @param task 任务
     */
    public static Future<?> submit(Runnable task) {
        return executor.submit(task);
    }


    /**
     * 异步执行任务没有返回结果
     *
     * @param task 任务结果
     */
    public static void execute(Runnable task) {
        executor.execute(task);
    }

    /**
     * 批量执行任务
     *
     * @param callables 执行任务
     * @param timeout   超时间为秒
     */
    public static <V> List<V> submit(List<Callable<V>> callables, long timeout) {
        List<Future<V>> futures;
        List<V> result = new ArrayList<V>();

        try {
            futures = executor.invokeAll(callables, timeout, TimeUnit.SECONDS);

            for (Future<V> future : futures) {
                try {
                    V v = future.get();
                    if (v != null) {
                        result.add(v);
                    }
                } catch (Exception e) {
                    log.error("submit error", e);
                }
            }
        } catch (InterruptedException e) {
            log.error("submit error", e);
        }

        return result;
    }

    /**
     * 批量执行任务
     *
     * @param callables 执行任务
     * @return 返回结果
     */
    public static <V> List<V> submit(List<Callable<V>> callables) {

        List<Future<V>> futures = new ArrayList<Future<V>>();
        List<V> result = new ArrayList<V>();

        try {
            futures = executor.invokeAll(callables);

            for (Future<V> future : futures) {
                try {
                    V v = future.get();
                    if (v != null) {
                        result.add(v);
                    }
                } catch (Exception e) {
                    log.error("submit error", e);
                }
            }
        } catch (InterruptedException e) {
            log.error("submit error", e);
        }

        return result;
    }

    /**
     * 关闭服务
     */
    public static void shutdown() {
        if (null != executor) {
            executor.shutdown();
        }
    }

    /**
     * 初始化线程池
     */
    private static void initExecutor() {
        if (executor == null) {
            synchronized (ThreadUtil.class) {
                if (executor == null) {
                    executor = new ThreadPoolExecutor(
                            Runtime.getRuntime().availableProcessors() * 2,
                            100,
                            1,
                            TimeUnit.MINUTES,
                            new ArrayBlockingQueue<>(10000),
                            new DefaultThreadFactory());
                    //停止监控
//                    executor.submit(new Monitor((ThreadPoolExecutor) executor));
                }
            }
        }
    }

    /**
     * 线程工厂
     *
     * @author
     */
    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final ThreadGroup threadGroup;
        private final String namePrefix;

        public DefaultThreadFactory() {
            SecurityManager sm = System.getSecurityManager();
            this.threadGroup = sm != null ? sm.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = "pool-" + DefaultThreadFactory.poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(this.threadGroup, r, this.namePrefix + this.threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }

    }

    static class Monitor implements Runnable {
        final ThreadPoolExecutor executor;

        public Monitor(ThreadPoolExecutor executor) {
            this.executor = executor;
        }

        @Override
        public void run() {
            while (!this.executor.isShutdown()) {
                if (log.isInfoEnabled()) {
                    log.info("" +
                            "poolCount={}," +
                            "activeCount={}," +
                            "coreSize={}," +
                            "maxSize={}," +
                            "largetsSize={}," +
                            "queueSize={}," +
                            "reminning={}," +
                            "completedCount={}",
                            executor.getPoolSize(),
                            executor.getActiveCount(),
                            executor.getCorePoolSize(),
                            executor.getMaximumPoolSize(),
                            executor.getLargestPoolSize(),
                            executor.getQueue().size(),
                            executor.getQueue().remainingCapacity(),
                            executor.getCompletedTaskCount()
                    );
                }
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

}
