package es.macero.cqgame.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "es.macero.cqgame")
@EnableAutoConfiguration
// Do not extend this class if using embedded server
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}