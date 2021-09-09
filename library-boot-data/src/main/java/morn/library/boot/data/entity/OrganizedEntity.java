package morn.library.boot.data.entity;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

/**
 * 组织成员映射类
 */
@Getter
@Setter
@FieldNameConstants
@MappedSuperclass
public class OrganizedEntity extends CreatorEntity {

  /**
   * 组织编号
   */
  @ApiModelProperty("组织机构编号")
  @Column
  protected Long departmentId;
}
