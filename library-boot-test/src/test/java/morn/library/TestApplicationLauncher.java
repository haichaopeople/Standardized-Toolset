package morn.library;

import morn.library.boot.data.jpa.crud.SpecificationRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import morn.library.boot.log.EnableOperateLog;
import morn.library.boot.netty.annotation.EnableNettyClient;
import morn.library.boot.netty.annotation.EnableNettyServer;
import morn.library.boot.notify.annotation.EnableNotify;
import morn.library.boot.template.annotation.EnableTemplate;

@SpringBootApplication
@EnableAsync
@EnableNotify
@EnableCaching
@EnableTemplate
@EnableOperateLog
@EnableJpaAuditing
@EnableNettyClient
@EnableNettyServer
@EnableTransactionManagement
@EnableJpaRepositories(repositoryFactoryBeanClass = SpecificationRepositoryFactoryBean.class)
public class TestApplicationLauncher {

  public static void main(String[] args) {
    SpringApplication.run(TestApplicationLauncher.class, args);
  }
}
