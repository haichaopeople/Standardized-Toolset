package morn.library.rest;

import io.swagger.annotations.ApiModel;
import morn.library.core.CriteriaMap;
import morn.library.util.GenericUtils;

/**
 * REST分页请求
 *
 * @see RestPageDefinition REST分页请求
 */
@ApiModel(value = "REST分页模型", description = "统一数据模型，通常用于请求体")
public class RestPage<M> extends RestModel<M> implements
    RestPageDefinition<RestPageable, M, CriteriaMap> {

  /**
   * 分页参数
   */
  private RestPageable pageable;

  public RestPage() {
    super();
    pageable = new RestPageable();
  }

  @Override
  public RestPageable getPageable() {
    return pageable;
  }

  @Override
  public <T extends RestPageDefinition<RestPageable, M, CriteriaMap>> T setPageable(
      RestPageable pageable) {
    this.pageable = pageable;
    return GenericUtils.castFrom(this);
  }
}
