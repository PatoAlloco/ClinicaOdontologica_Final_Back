package com.clinica.odontologica.security;

import com.clinica.odontologica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConf extends WebSecurityConfigurerAdapter {
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public WebSecurityConf(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider( daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/turnosAgregar.html", "/turnosList.html").access("hasRole('ADMIN') or hasRole('USER')")
                .antMatchers( "/pacientesList.html","/pacienteAgregar.html","/odontologosList.html","/odontologoAgregar.html").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().
                formLogin()
                .successHandler(authenticationSuccessHandler)
                .permitAll()
                .and().logout();
    }
}

// http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/turnos").hasRole("USER")
////                .antMatchers("/pacientes").hasRole("ROLE_ADMIN")
////                .antMatchers("/odontologos").hasRole("ROLE_ADMIN") //chequear esto
////                .and().authorizeRequests().antMatchers("/pacientes").hasRole("ADMIN")
////                .and().authorizeRequests().antMatchers("/odontologos").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and().formLogin()
//                .and().logout();