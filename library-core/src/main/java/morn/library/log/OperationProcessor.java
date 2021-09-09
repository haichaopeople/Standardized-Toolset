package morn.library.log;

/**
 * 操作日志处理器
 *
 */
@FunctionalInterface
public interface OperationProcessor {

  /**
   * 处理操作日志
   *
   * @param meta 操作日志元数据
   * @param operation 操作日志实例
   */
  void handle(OperateMeta meta, Operation operation);
}
