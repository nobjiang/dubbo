package cucn.epoch.dubbo.tccproducer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class TccProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TccProducerApplication.class, args);
    }

}
