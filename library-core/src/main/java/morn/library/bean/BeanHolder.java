package morn.library.bean;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import morn.library.bean.support.SimpleAnnotationFeature;

/**
 * 标识的实例持有者
 *
 * @param <T> 实例类型
 */
@Getter
@Setter
public class BeanHolder<T> extends SimpleAnnotationFeature {

  /**
   * 实例
   */
  private T bean;

  /**
   * 方法集合
   */
  private List<FunctionHolder> functionHolders;

  public BeanHolder(AnnotationFeature identify, T bean,
      List<FunctionHolder> functionHolders) {
    super(identify);
    this.bean = bean;
    this.functionHolders = functionHolders;
  }
}
