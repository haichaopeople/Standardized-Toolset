package morn.library.tree;

/**
 * 树形节点
 *
 */
public interface TreeNode {

  Long getId();

  Long getParentId();

  String getName();

  Integer getLevel();

  void setId(Long id);

  void setParentId(Long parentId);

  void setName(String name);

  void setLevel(Integer level);
}
