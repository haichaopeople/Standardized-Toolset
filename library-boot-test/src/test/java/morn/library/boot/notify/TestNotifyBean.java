package morn.library.boot.notify;

import lombok.extern.slf4j.Slf4j;
import morn.library.constant.ApplicationConstants;
import morn.library.core.CriteriaMap;
import morn.library.notify.annotation.Notify;
import morn.library.notify.annotation.NotifyReceiver;
import morn.library.notify.support.NotifyArgs;
import morn.library.notify.support.NotifyArguments;
import morn.library.template.annotation.Template;
import org.springframework.stereotype.Component;

/**
 * 测试通知
 *
 */
@Slf4j
@Component
public class TestNotifyBean {

  /**
   * 向角色发送邮箱验证码
   */
  @Notify(type = "email", name = "captcha")
  @NotifyReceiver(type = "role", value = {"r1", "r2"})
  public void captchaToRoleNoTemplate() {
    log.info("Do some thing and Notify to role.");
  }

  /**
   * 向角色发送邮箱验证码
   */
  @Template(type = ApplicationConstants.TemplateTypes.RESOURCE)
  @Notify(type = "email", name = "captcha")
  @NotifyReceiver(type = "role", value = {"r1", "r2"})
  public void captchaToRole() {
    // 设置模板参数
    CriteriaMap templateArgs = NotifyArguments.getTemplateArgs();
    templateArgs.put("role", "管理员");
    templateArgs.put("captcha", "a1b2c3");
  }

  /**
   * 向用户发送短信验证码
   */
  @Template(type = ApplicationConstants.TemplateTypes.RESOURCE)
  @Notify(type = "sms", name = "captcha")
  @NotifyReceiver(type = "user")
  public void captchaToUser() {
    // 设置通知参数
    NotifyArgs notifyArgs = NotifyArguments.getNotifyArgs();
    notifyArgs.addReceiverValue("u1");
    notifyArgs.addReceiverValue("u2");
    // 设置模板参数
    CriteriaMap templateArgs = NotifyArguments.getTemplateArgs();
    templateArgs.put("phone", "0041");
    templateArgs.put("captcha", "a3b2c1");
  }
}
