package client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 *
 *
 * @author wangwenjie
 * @date 2020-11-05
 */
@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ClientApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();
        if (activeProfiles.length > 0) {
            String activeProfile = activeProfiles[0];
            System.out.println("==> 当前生效的配置文件 = " + activeProfile);
        }
        Map<String, Object> systemProperties = environment.getSystemProperties();
        System.out.println(systemProperties);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
