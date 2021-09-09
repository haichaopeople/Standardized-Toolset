package morn.library.exception;

/**
 * 异常处理器
 */
public interface ExceptionInterpreterService {

  /**
   * 解释异常
   *
   * @return 应用消息
   */
  <T extends Exception> ApplicationMessage interpret(T exception);
}
