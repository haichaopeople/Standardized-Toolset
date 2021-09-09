package morn.library.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

import morn.library.exception.ApplicationMessages;
import morn.library.task.TaskExecutors;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 异步工具类单元测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AsyncUtilsTest {

  @Test
  public void await1() {
    CountDownLatch latch = new CountDownLatch(1);
    TaskExecutors.submit(latch::countDown);
    try {
      AsyncUtils.await(latch);
    } catch (Exception ignore) {
      Assert.fail();
    }
  }

  @Test
  public void getFutureResult1() {
    CompletableFuture<String> future = TaskExecutors.submit(() -> "This is result.");
    String result = AsyncUtils.getFutureResult(future);
    Assert.assertEquals("This is result.", result);
  }

  @Test
  public void getFutureResult2() {
    CompletableFuture<Object> future = TaskExecutors.submit(() -> {
      throw ApplicationMessages.buildException("This is Exception.");
    });
    Object result = AsyncUtils.getFutureResult(future);
    Assert.assertNull(result);
  }
}