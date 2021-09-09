package morn.library.rest;

import java.util.Objects;

import morn.library.bean.annotation.Source;
import morn.library.bean.annotation.Target;
import morn.library.rest.constant.RestMessageConstants;
import morn.library.rest.constant.RestMessageLevel;
import morn.library.rest.support.SimpleRestMessage;
import org.springframework.stereotype.Component;

/**
 * 百度消息转换器
 */
@Component
@Source(RestMessage.class)
@Target(BaiduMessage.class)
public class BaiduMessageConverter implements RestMessageConverter<BaiduMessage> {

  @Override
  public BaiduMessage convert(RestMessage restMessage) {
    BaiduMessage baiduMessage = new BaiduMessage();
    baiduMessage.setError(RestMessageConstants.isSuccess(restMessage.getStatus()) ? "0" : "-1");
    baiduMessage.setMsg(restMessage.getMessage());
    baiduMessage.setData(restMessage.getData());
    return baiduMessage;
  }

  @Override
  public RestMessage revert(BaiduMessage baiduMessage) {
    RestMessage restMessage = new SimpleRestMessage();
    boolean success = isSuccess(baiduMessage);
    restMessage.setStatus(success ? RestMessageConstants.SUCCESS : RestMessageConstants.FAILURE);
    restMessage.setLevel(success ? RestMessageLevel.INFO : RestMessageLevel.ERROR);
    restMessage.setCode(baiduMessage.getError());
    restMessage.setMessage(baiduMessage.getMsg());
    restMessage.setData(baiduMessage.getData());
    return restMessage;
  }

  private boolean isSuccess(BaiduMessage baiduMessage) {
    return Objects.equals(baiduMessage.getError(), "0");
  }
}
