package cucn.epoch.dubbo.tccconsumer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import cucn.epoch.dubbo.tccconsumer.transaction.SetServiceConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDubboConfiguration
public class TccConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TccConsumerApplication.class, args);
        // 测试分布式事务使用
        SetServiceConsumer setServiceConsumer = (SetServiceConsumer) run.getBean("setServiceConsumer");
        setServiceConsumer.buy("NIKE");
    }

}
