package morn.library.task;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

import morn.library.bean.support.BeanCaches;
import org.springframework.util.Assert;

/**
 * 异步任务调度者
 *
 */
public class TaskExecutors {

  private TaskExecutors() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  /**
   * 发布任务
   *
   * @param task 任务
   * @return 异步结果
   */
  public static CompletableFuture<Void> submit(Runnable task) {
    CompletableTaskExecutor executor = BeanCaches.bean(CompletableTaskExecutor.class);
    Assert.notNull(executor, "尚未注册任务发布者");
    return executor.submit(task);
  }

  /**
   * 发布任务
   *
   * @param task 任务
   * @param <V>  结果类型
   * @return 异步结果
   */
  public static <V> CompletableFuture<V> submit(Callable<V> task) {
    CompletableTaskExecutor executor = BeanCaches.bean(CompletableTaskExecutor.class);
    Assert.notNull(executor, "尚未注册任务发布者");
    return executor.submit(task);
  }

  /**
   * 构建任务组执行器
   */
  public static <V> TaskGroupExecutor<V> groupExecutor(int size) {
    CompletableTaskExecutor executor = BeanCaches.bean(CompletableTaskExecutor.class);
    return new TaskGroupExecutor<>(executor, size);
  }
}
