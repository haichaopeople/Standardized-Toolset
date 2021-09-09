package morn.library.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import morn.library.translate.Transfer;
import morn.library.translate.Translators;

/**
 * 应用消息构建器
 */
@RequiredArgsConstructor
public class ApplicationMessages {

  /**
   * 默认异常编码
   */
  public static final String FAILURE_MESSAGE = "系统异常";

  public static final String FAILURE_CODE = "-1";

  /**
   * 翻译载体
   */
  private final Transfer transfer;

  /**
   * 消息内容
   */
  private String message;

  /**
   * 解决方案
   */
  private String solution;

  /**
   * 构建应用消息
   *String
   * @param code    消息编码
   * @param message 消息内容
   * @return 应用消息
   * @deprecated {@link #buildMessage(String, String)}
   */
  @Deprecated
  public static ApplicationMessage build(String code, String message) {
    return buildMessage(code, message, null);
  }

  /**
   * 构建应用消息
   *
   * @param code     消息编码
   * @param message  消息内容
   * @param solution 解决方案
   * @return 应用消息
   * @deprecated {@link #buildMessage(String, String, String)}
   */
  @Deprecated
  public static ApplicationMessage build(String code, String message, String solution) {
    return buildMessage(code, message, solution);
  }

  /**
   * 构建应用异常
   *
   * @param message 消息内容
   * @return 应用消息
   */
  public static ApplicationException buildException(String message) {
    return buildMessage(null, message, null).exception();
  }

  /**
   * 构建应用异常
   *
   * @param code    消息编码
   * @param message 消息内容
   * @return 应用消息
   */
  public static ApplicationException buildException(String code, String message) {
    return buildMessage(code, message, null).exception();
  }

  /**
   * 构建应用异常
   *
   * @param code     消息编码
   * @param message  消息内容
   * @param solution 解决方案
   * @return 应用消息
   */
  public static ApplicationException buildException(String code, String message, String solution) {
    return buildMessage(code, message, solution).exception();
  }

  /**
   * 构建应用消息
   *
   * @param message 消息内容
   * @return 应用消息
   */
  public static ApplicationMessage buildMessage(String message) {
    return buildMessage(null, message, null);
  }

  /**
   * 构建应用消息
   *
   * @param code    消息编码
   * @param message 消息内容
   * @return 应用消息
   */
  public static ApplicationMessage buildMessage(String code, String message) {
    return buildMessage(code, message, null);
  }

  /**
   * 构建应用消息
   *
   * @param code     消息编码
   * @param message  消息内容
   * @param solution 解决方案
   * @return 应用消息
   */
  public static ApplicationMessage buildMessage(String code, String message, String solution) {
    return withMessage(code, message).solution(solution).build();
  }

  /**
   * 翻译应用异常
   *
   * @param name 消息名称
   * @param args 消息参数
   */
  public static ApplicationException translateException(String name, Object... args) {
    return translateMessage(name, args).exception();
  }

  /**
   * 翻译应用消息
   *
   * @param name 消息名称
   * @param args 消息参数
   * @return 应用消息
   */
  public static ApplicationMessage translateMessage(String name, Object... args) {
    return withTransfer(name, args).build();
  }

  /**
   * 翻译应用异常，支持缺省值
   *
   * @param code           消息编码
   * @param defaultExpress 默认消息表达式
   * @param args           消息参数
   * @return 应用异常
   */
  public static ApplicationException translateDefaultException(String code, String defaultExpress,
      Object... args) {
    return translateDefaultMessage(code, defaultExpress, args).exception();
  }

  /**
   * 翻译应用消息，支持缺省值
   *
   * @param name           消息名称
   * @param defaultExpress 默认消息表达式
   * @param args           消息参数
   * @return 应用消息
   */
  public static ApplicationMessage translateDefaultMessage(String name, String defaultExpress,
      Object... args) {
    String defaultMessage = String.format(defaultExpress, args);
    return withTransfer(name, args).defaultMessage(defaultMessage).build();
  }

  /**
   * 生成构建器，用于翻译应用消息
   *
   * @param name 消息名称
   * @param args 消息参数
   * @return 应用消息构建器
   */
  public static ApplicationMessages withTransfer(String name, Object... args) {
    String n = StringUtils.isEmpty(name) ? FAILURE_CODE : name;
    return builder().name(n).arguments(args);
  }

  /**
   * 生成构建器，用于直接生成应用消息
   *
   * @param code    消息编码
   * @param message 消息内容
   * @return 应用消息构建器
   */
  public static ApplicationMessages withMessage(String code, String message) {
    String c = StringUtils.isEmpty(code) ? FAILURE_CODE : code;
    String m = StringUtils.isEmpty(message) ? FAILURE_MESSAGE : message;
    return builder().code(c).message(m);
  }

  /**
   * 获取应用消息构建器
   *
   * @return 应用消息构建器
   */
  private static ApplicationMessages builder() {
    return new ApplicationMessages(Transfer.builder().build());
  }

  /**
   * 设置消息名称
   *
   * @param name 消息名称
   * @return 应用消息构建器
   */
  public ApplicationMessages name(String name) {
    transfer.setName(name);
    return this;
  }

  /**
   * 设置消息编码
   *
   * @param code 消息编码
   * @return 应用消息构建器
   */
  public ApplicationMessages code(String code) {
    transfer.setCode(code);
    return this;
  }

  /**
   * 设置消息参数
   *
   * @param args 消息参数
   * @return 应用消息构建器
   */
  private ApplicationMessages arguments(Object... args) {
    transfer.setArgs(args);
    return this;
  }

  /**
   * 设置消息内容
   *
   * @param message 消息内容
   * @return 应用消息构建器
   */
  public ApplicationMessages message(String message) {
    this.message = message;
    return this;
  }

  /**
   * 设置缺省消息内容
   *
   * @param defaultMessage 缺省消息内容
   * @return 应用消息构建器
   */
  public ApplicationMessages defaultMessage(String defaultMessage) {
    transfer.setDefaultMessage(defaultMessage);
    return this;
  }

  /**
   * 设置解决方案
   *
   * @param solution 解决方案
   * @return 应用消息构建器
   */
  public ApplicationMessages solution(String solution) {
    this.solution = solution;
    return this;
  }

  /**
   * 从外部构建应用消息
   *
   * @return 应用消息
   */
  public ApplicationMessage generate() {
    return build();
  }

  /**
   * 构建应用消息
   *
   * @return 应用消息
   */
  private ApplicationMessage build() {
    if (StringUtils.isEmpty(message) && StringUtils.isEmpty(solution)) {
      // 当未设置message/solution时，通过Translator构建应用消息
      ApplicationMessage translate = Translators.defaultTranslator()
          .translate(transfer, ApplicationMessage.class);
      return new ApplicationMessage().setCode(translate.getCode())
          .setMessage(translate.getMessage()).setSolution(translate.getSolution());
    }
    // 当设置了message/solution时，直接构建应用消息
    return new ApplicationMessage().setCode(transfer.getCode()).setMessage(message)
        .setSolution(solution);
  }
}
