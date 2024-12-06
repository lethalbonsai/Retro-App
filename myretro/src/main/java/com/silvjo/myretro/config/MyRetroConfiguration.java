package com.silvjo.myretro.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@EnableConfigurationProperties({MyRetroProperties.class})
@Configuration
public class MyRetroConfiguration {

    Logger logger = LoggerFactory.getLogger(MyRetroConfiguration.class);

    @Value("${service.users.server}")
    String server;
    @Value("${service.users.port}")
    Integer port;
    @Value("${service.users.username}")
    String username;
    @Value("${service.users.password}")
    String password;

    @Bean
    ApplicationListener<ApplicationReadyEvent> init(MyRetroProperties myRetroProperties){
        return event -> {
            logger.info("\nThe users service properties are: \n- Server: {}\n- Port: {}\n- Username: {}\n- Password: {}",
                    myRetroProperties.getUsers().getServer(),
                    myRetroProperties.getUsers().getPort(),
                    myRetroProperties.getUsers().getUsername(),
                    myRetroProperties.getUsers().getPassword());
        };
    }
}
