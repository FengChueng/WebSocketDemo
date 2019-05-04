package com.zyl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.lang.management.ManagementFactory;

@Slf4j
@SpringBootApplication
public class WebsocketApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(WebsocketApplication.class, args);
//	}

    private static ApplicationContext applicationContext = null;

    public static void main(String[] args) {
        String mode = args != null && args.length > 0 ? args[0] : null;

        log.debug("PID:" + ManagementFactory.getRuntimeMXBean().getName() + " Application mode:" + mode + " context:"
                + applicationContext);
        if (applicationContext != null && mode != null && "stop".equals(mode)) {
            System.exit(SpringApplication.exit(applicationContext, new ExitCodeGenerator() {
                @Override
                public int getExitCode() {
                    return 0;
                }
            }));
        } else {
            SpringApplication app = new SpringApplication(WebsocketApplication.class);
            applicationContext = app.run(args);
            log.debug("PID:" + ManagementFactory.getRuntimeMXBean().getName() + " Application started context:" +
                    applicationContext);
        }
    }

}
