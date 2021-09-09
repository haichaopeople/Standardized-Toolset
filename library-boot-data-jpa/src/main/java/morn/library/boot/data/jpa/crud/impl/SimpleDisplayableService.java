package morn.library.boot.data.jpa.crud.impl;

import java.io.Serializable;

import morn.library.boot.data.jpa.crud.SpecificationRepository;
import morn.library.boot.data.CrudService;
import morn.library.boot.data.DisplayableService;
import morn.library.data.Displayable;
import morn.library.util.GenericUtils;

/**
 * 显示/隐藏服务实现
 */
public class SimpleDisplayableService<T extends Displayable, I extends Serializable, R extends SpecificationRepository<T, I>> extends
    SimpleCrudService<T, I, R> implements CrudService<T, I>, DisplayableService<T, I> {

  @Override
  public <S extends T> S toggleDisplay(I id, boolean isDisplay) {
    T result = repository().findById(id).map(model -> toggleDisplay(model, isDisplay)).orElse(null);
    return GenericUtils.castFrom(result);
  }

  /**
   * 切换显示状态
   */
  protected T toggleDisplay(T model, boolean isDisplay) {
    model.setDisplay(isDisplay);
    return update(model);
  }
}
