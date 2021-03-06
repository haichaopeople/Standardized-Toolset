package morn.library.boot.data;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.io.Serializable;

import morn.library.log.OperateAction;
import morn.library.rest.RestMessage;
import morn.library.rest.RestModel;
import morn.library.rest.RestPage;
import morn.library.util.GenericUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import morn.library.boot.rest.RestBuilders;
import morn.library.data.group.Add;
import morn.library.data.group.Delete;
import morn.library.data.group.Put;
import morn.library.data.group.Search;
import morn.library.data.group.Update;

/**
 * 基础控制器实现
 */
public class CrudController<T, I extends Serializable, S extends CrudService<T, I>> {

  /**
   * 基础服务
   */
  @Autowired
  private CrudService<T, I> service;

  protected S service() {
    return GenericUtils.castFrom(service);
  }

  /**
   * 查询
   */
  @ApiOperation("单体查询")
  @ApiImplicitParam(name = "id", value = "主键")
  @GetMapping("{id}")
  public RestMessage get(@PathVariable I id) {
    T model = service().get(id);
    return RestBuilders.successMessage(model);
  }

  /**
   * 新增
   */
  @ApiOperation("新增")
  @OperateAction(OperateAction.ActionConstants.ADD)
  @PostMapping
  public RestMessage add(@Validated(Add.class) @RequestBody RestModel<T> restModel) {
    T model = service().add(restModel);
    return RestBuilders.successMessage(model);
  }

  /**
   * 修改
   */
  @ApiOperation("修改")
  @OperateAction(OperateAction.ActionConstants.UPDATE)
  @PutMapping
  public RestMessage update(
      @Validated({Update.class, Put.class}) @RequestBody RestModel<T> restModel) {
    T model = service().update(restModel);
    return RestBuilders.successMessage(model);
  }

  /**
   * 搜索
   *
   * @param restPage REST分页参数
   * @return REST消息
   */
  @ApiOperation("分页搜索")
  @PostMapping("search")
  public RestMessage search(@Validated(Search.class) @RequestBody RestPage<T> restPage) {
    Page<T> page = service.search(restPage);
    return RestBuilders.successMessage(page);
  }

  /**
   * 删除
   */
  @ApiOperation("删除")
  @ApiImplicitParam(name = "id", value = "主键")
  @OperateAction(OperateAction.ActionConstants.DELETE)
  @DeleteMapping("/{id}")
  public RestMessage delete(@Validated({Delete.class}) @PathVariable I id) {
    service().delete(id);
    return RestBuilders.successMessage();
  }
}
