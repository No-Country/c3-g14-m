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
        http.authorizeRequests()
        .antMatchers("/login").permitAll()
        .antMatchers("/admin/register/professionals").hasRole("ADMIN") //REVISAR SI NO FUNCIONA hasRole probar con hasAuthority
        .antMatchers("/admin/bookings").hasRole("ADMIN");
        //LUEGO AGREGAR LOS PROXIMOS ENDPOINTS

        http.formLogin()
        .usernameParameter("email")
        .passwordParameter("password")
        .loginPage("/login");

        http.logout().logoutUrl("/logout");
        

    }
    
    

}
