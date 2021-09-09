package morn.library.task;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

import morn.library.bean.annotation.Objective;
import org.springframework.core.task.AsyncListenableTaskExecutor;

/**
 * 基础任务发布者
 */
@Objective
public class SimpleCompletableTaskExecutor implements CompletableTaskExecutor {

  private final AsyncListenableTaskExecutor taskExecutor;

  public SimpleCompletableTaskExecutor(AsyncListenableTaskExecutor taskExecutor) {
    this.taskExecutor = taskExecutor;
  }

  @SuppressWarnings("unchecked")
  @Override
  public CompletableFuture<Void> submit(Runnable task) {
    return (CompletableFuture<Void>) taskExecutor.submitListenable(task).completable();
  }

  @Override
  public <V> CompletableFuture<V> submit(Callable<V> task) {
    return taskExecutor.submitListenable(task).completable();
  }
}
