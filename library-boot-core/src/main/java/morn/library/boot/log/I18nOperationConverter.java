package morn.library.boot.log;

import morn.library.log.*;
import morn.library.translate.Translator;
import morn.library.log.OperateMeta;
import morn.library.log.OperateMode;
import morn.library.log.Operation;
import morn.library.log.OperationConverter;

/**
 * 国际化操作日志转换器
 */
@OperateMode(OperateModes.I18N)
public class I18nOperationConverter implements OperationConverter {

  /**
   * 操作日志配置项
   */
  private final OperateProperties properties;

  /**
   * 翻译器
   */
  private final Translator translator;

  public I18nOperationConverter(OperateProperties properties,
      Translator translator) {
    this.properties = properties;
    this.translator = translator;
  }

  @Override
  public Operation convert(OperateMeta operateMeta) {
    // 拼接国际化编码
    OperateProperties.I18n i18n = properties.getI18n();
    String messageCode =
        i18n.getPrefix() + i18n.getDelimiter() + operateMeta.getGroupName() + i18n.getDelimiter()
            + operateMeta.getActionName();
    // 生成操作内容
    String content = translator.translate(messageCode, operateMeta.getActionArgs());
    // 构建操作实例
    Operation operation = new Operation();
    operation.setContent(content);
    return operation;
  }
}
