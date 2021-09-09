package morn.library.boot.log;

import morn.library.log.*;
import morn.library.log.OperateMeta;
import morn.library.log.OperateMode;
import morn.library.log.Operation;
import morn.library.log.OperationConverter;

/**
 * 默认化操作日志转换器
 */
@OperateMode(OperateModes.SIMPLE)
public class SimpleOperationConverter implements OperationConverter {

  /**
   * 操作日志配置项
   */
  private final OperateProperties properties;

  public SimpleOperationConverter(OperateProperties properties) {
    this.properties = properties;
  }

  @Override
  public Operation convert(OperateMeta operateMeta) {
    // 生成操作内容
    StringBuilder builder = new StringBuilder(operateMeta.getActionName());
    Object[] actionArgs = operateMeta.getActionArgs();
    OperateProperties.Simple simple = properties.getSimple(); // 简易模式配置项
    for (int i = 0; i < actionArgs.length; i++) {
      Object actionArg = actionArgs[i];
      // 根据下标拼接占位符
      String placeholder = simple.getPlaceholderPrefix() + i + simple.getPlaceholderSuffix();
      int pl = placeholder.length();
      int index = builder.indexOf(placeholder);
      if (index == -1) {
        break;
      }
      builder.replace(index, index + pl, actionArg.toString());
    }
    // 构建操作实例
    Operation operation = new Operation();
    operation.setContent(builder.toString());
    return operation;
  }
}
