package morn.library.boot.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import morn.library.boot.rest.RestBuilders;
import morn.library.boot.web.Responses;
import morn.library.rest.RestMessage;

/**
 * 认证失败处理者
 *
 */
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) {
    RestMessage message = RestBuilders.failureBuilder().code("login.failure")
        .message(exception.getMessage()).build();
    Responses.from(response).json().respond(message);
  }
}
