package morn.library.rest;

/**
 * REST配置
 *
 */
public interface RestConfigurer {

  /**
   * 添加序列消息类
   *
   * <p>注册为序列消息的对象，系统不会对其进行二次包装。否则，该对象通常置入{@link RestMessage#setData(Object)}进行传输</p>
   *
   * @param registry 序列消息注册表
   * @see SerialMessageRegistry
   */
  default void addSerialMessageClass(SerialMessageRegistry registry) {
  }
}
