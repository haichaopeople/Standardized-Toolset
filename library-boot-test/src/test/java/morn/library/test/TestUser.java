package morn.library.test;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import morn.library.boot.data.entity.OrganizedEntity;
import morn.library.data.Displayable;
import morn.library.data.group.Add;

/**
 * 测试用户
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TestUser extends OrganizedEntity implements Displayable, Serializable {


  @Id
  private Long id;

  @Column
  @NotNull
  private String username;

  @Column
  @NotNull(groups = Add.class)
  private String password;

  @Column
  private Boolean display;

  public TestUser(Long id, @NotNull String username) {
    this.id = id;
    this.username = username;
  }
}
