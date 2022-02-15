package com.clinica.sgt.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebAuthorization extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Restricciones al acceso dependiendo el rol del usuario
        http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/login").permitAll()
        .antMatchers("/admin/**").hasAuthority("ADMIN") //REVISAR SI NO FUNCIONA hasAuthority probar con hasRole
        //.antMatchers("/admin/bookings").hasAuthority("ADMIN");

        //LUEGO AGREGAR LOS PROXIMOS ENDPOINTS

        http.formLogin()
        .usernameParameter("email")
        .passwordParameter("password")
        .loginPage("/login")
        .and()
        .logout().logoutUrl("/logout").permitAll();
        

    }
    
    

}
