package morn.library.data;

/**
 * 持久化树形节点
 */
public interface PersistTreeNode {

  /**
   * 获取查询码
   */
  String getSearchCode();

  /**
   * 设置查询码
   */
  void setSearchCode(String searchCode);
}
