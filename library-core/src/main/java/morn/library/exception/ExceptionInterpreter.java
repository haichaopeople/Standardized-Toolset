package morn.library.exception;

/**
 * 异常解释器
 */
@FunctionalInterface
public interface ExceptionInterpreter {

  /**
   * 解释异常
   *
   * @param throwable 异常
   * @param args      参数
   * @return 应用消息
   */
  ApplicationMessage interpret(Throwable throwable, Object... args);
}
