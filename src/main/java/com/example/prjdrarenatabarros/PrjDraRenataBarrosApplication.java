package com.example.prjdrarenatabarros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@AutoConfigurationPackage
@SpringBootApplication
@EntityScan(basePackages = "com.example.prjdrarenatabarros.domain")
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = {"com.example.prjdrarenatabarros.domain.repository"})
@EnableTransactionManagement
public class PrjDraRenataBarrosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrjDraRenataBarrosApplication.class, args);
    }

}
