package morn.library.boot.web.log;

import static morn.library.util.AnnotationFeatureUtils.WILDCARD;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import morn.library.bean.annotation.Tag;
import morn.library.boot.web.Requests;
import morn.library.boot.web.Servlets;
import morn.library.log.OperateMeta;
import morn.library.log.Operation;
import morn.library.log.OperationAdapter;
import org.springframework.core.annotation.Order;
import morn.library.core.Generic;

/**
 * Web操作适配器
 */
@Order
@Tag(WILDCARD)
public class WebOperationAdapter implements OperationAdapter {

  @Override
  public void adaption(OperateMeta meta, Operation operation) {
    // 记录请求和响应对象
    HttpServletRequest request = Servlets.request();
    meta.setRequest(Generic.from(request));
    HttpServletResponse response = Servlets.response();
    meta.setResponse(Generic.from(response));
    // 记录请求信息
    Requests requests = Requests.from(request);
    operation.setRequestUrl(requests.getUrl()); // 请求地址
    operation.setRequestMethod(requests.getMethod()); // 请求方法
    operation.setRequestContentType(requests.getContentType()); // 请求内容类型
    operation.setOperatorIp(requests.getIp()); // 访问者IP
    operation.setOperatorAgent(requests.getUserAgent()); // 客户端信息
  }
}
