package morn.library.boot.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import morn.library.boot.rest.RestBuilders;
import morn.library.boot.web.Responses;
import morn.library.rest.RestMessage;

/**
 * 认证成功处理者
 *
 */
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {
    RestMessage message = RestBuilders.successMessage("login.success");
    Responses.from(response).json().respond(message);
  }
}
