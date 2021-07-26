package com.example.prjdrarenatabarros.security;

import com.example.prjdrarenatabarros.services.imp.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImp usuarioServiceImp;

//    @Bean
//    public static BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Override // configura as solcitaçoes de acesso por http
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable() //desativa as configuraçoes padrao de momoria
                .authorizeRequests() //permitir restrigir acesso
                .antMatchers(HttpMethod.GET, "/login").permitAll() // qualquer usuario acessa a pagina
                .antMatchers(HttpMethod.GET, "/gerenciamento-usuario").hasAnyRole("ADMIN") // so usuarios adm pode acessar a pagina
                .anyRequest().authenticated()
                .and().formLogin().permitAll()  //permite qualquer usuario
                .loginPage("/login").defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .and().logout() //mapeia URL de logaout e invalida usuario autenticado
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout=true");
    }

    @Override // Cria autenticacao do usuario com banco de dados em memoria
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioServiceImp).passwordEncoder(new BCryptPasswordEncoder());

//        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .withUser("alex")
//                .password("123")
//                .roles("ADMIN");
    }

    @Override // iginora URL especificas
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }
}
