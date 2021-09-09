package morn.library.boot.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import morn.library.bean.annotation.Objective;
import morn.library.boot.web.Servlets;
import morn.library.log.OperateMeta;
import morn.library.log.Operation;
import morn.library.log.OperationProcessor;
import org.springframework.stereotype.Component;

/**
 * 测试操作处理器
 */
@Slf4j
@Component
@Objective
public class TestOperationProcessor implements OperationProcessor {

  @Override
  public void handle(OperateMeta operateMeta, Operation operation) {
    log.info("Operate|元数据：{}", operateMeta.toString());
    log.info("Operate|操作日志：{}", operation.toString());
    HttpSession session = Servlets.session();
    HttpServletRequest request = Servlets.request();
    HttpServletResponse response = Servlets.response();
    log.info("Operate|Session：{}", session);
    log.info("Operate|Request：{}", request);
    log.info("Operate|Response：{}", response);
    OperateAspectTest.DONE.set(true);
  }
}

