package morn.library.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;

import morn.library.core.CriteriaMap;
import morn.library.util.GenericUtils;

/**
 * REST数据模型
 *
 */
@ApiModel(value = "REST数据模型", description = "统一数据模型，通常用于请求体")
public class RestModel<M> implements RestModelDefinition<M, CriteriaMap> {


  /**
   * 数据模型
   */
  @ApiModelProperty("数据模型")
  @Valid
  private M model;

  /**
   * 附加数据
   */
  @ApiModelProperty("附加数据")
  private CriteriaMap attach;

  public RestModel() {
    this.attach = new CriteriaMap();
  }

  @Override
  public M getModel() {
    return model;
  }

  @Override
  public <T extends RestModelDefinition<M, CriteriaMap>> T setModel(M model) {
    this.model = model;
    return GenericUtils.castFrom(this);
  }

  @Override
  public CriteriaMap getAttach() {
    return attach;
  }

  @Override
  public <T extends RestModelDefinition<M, CriteriaMap>> T setAttach(CriteriaMap attach) {
    this.attach = attach;
    return GenericUtils.castFrom(this);
  }
}
