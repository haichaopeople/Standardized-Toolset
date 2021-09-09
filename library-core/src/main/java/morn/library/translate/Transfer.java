package morn.library.translate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 翻译载体
 */
@Builder
@Getter
@Setter
public class Transfer {

  /**
   * 翻译名称
   */
  private String name;

  /**
   * 国际化编码
   */
  private String code;

  /**
   * 国际化参数
   */
  private Object[] args;

  /**
   * 默认消息
   */
  private String defaultMessage;
}
