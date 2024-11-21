package pers.chenjiahao.codegenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import pers.chenjiahao.codegenerator.service.GeneratorBizGroupService;

@SpringBootApplication
public class CodeGeneratorApplication {

    @Autowired
    private GeneratorBizGroupService generatorService;

    public static void main(String[] args) {
        SpringApplication.run(CodeGeneratorApplication.class, args);
    }

    @Bean
    @Order(1)
    public CommandLineRunner GeneratorRunner(ApplicationContext ctx) {
        return args -> generatorService.execute();
    }
}
