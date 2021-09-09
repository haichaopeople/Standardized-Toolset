package morn.library.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Delegate;

/**
 * 应用异常
 */
@RequiredArgsConstructor
@Getter
@Setter
public class ApplicationException extends RuntimeException {

  /**
   * 异常消息
   */
  @Delegate
  private final transient ApplicationMessage applicationMessage;
}
