package morn.library.log;

import morn.library.core.BeanAdapter;

/**
 * 操作适配器
 *
 */
public interface OperationAdapter extends BeanAdapter<OperationContext> {

  /**
   * 适配
   *
   * @param meta      元数据
   * @param operation 操作实例
   */
  void adaption(OperateMeta meta, Operation operation);

  @Override
  default OperationContext adaption(OperationContext context) {
    adaption(context.getMeta(), context.getOperation());
    return context;
  }
}
