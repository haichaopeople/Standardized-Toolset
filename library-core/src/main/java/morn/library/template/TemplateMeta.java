package morn.library.template;

import lombok.Data;
import morn.library.core.CriteriaMap;

/**
 * 模板元数据
 */
@Data
public class TemplateMeta {

  /**
   * 模板类型
   */
  private String type;

  /**
   * 模板名称
   */
  private String name;

  /**
   * 模板参数
   */
  private CriteriaMap args;
}
