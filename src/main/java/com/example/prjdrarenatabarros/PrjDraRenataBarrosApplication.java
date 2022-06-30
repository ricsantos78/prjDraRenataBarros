package com.example.prjdrarenatabarros;

import com.example.prjdrarenatabarros.domain.entity.Especialidade;
import com.example.prjdrarenatabarros.domain.entity.Role;
import com.example.prjdrarenatabarros.domain.entity.Usuario;
import com.example.prjdrarenatabarros.services.EspecialidadeService;
import com.example.prjdrarenatabarros.services.RoleService;
import com.example.prjdrarenatabarros.services.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.UUID;

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
