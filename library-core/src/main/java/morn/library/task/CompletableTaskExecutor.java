package morn.library.task;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * 异步任务执行器
 */
public interface CompletableTaskExecutor {

  /**
   * 发布任务
   *
   * @param task 任务
   * @return 异步结果
   */
  CompletableFuture<Void> submit(Runnable task);

  /**
   * 发布任务
   *
   * @param task 任务
   * @param <V>  结果类型
   * @return 异步结果
   */
  <V> CompletableFuture<V> submit(Callable<V> task);
}
