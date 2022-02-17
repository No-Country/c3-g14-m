package com.clinica.sgt.configurations;

import com.clinica.sgt.servicios.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;   //Metodo para instanciar encoder
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); //Para mayor seguridad se encripta la password
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Restricciones al acceso dependiendo el rol del usuario
        http.authorizeRequests()
        .antMatchers("/index").permitAll()
        .antMatchers("/login").permitAll()
        .antMatchers("/admin/register/professionals").hasRole("ADMIN") //REVISAR SI NO FUNCIONA hasRole probar con hasAuthority
        .antMatchers("/admin/bookings").hasRole("ADMIN");
        //LUEGO AGREGAR LOS PROXIMOS ENDPOINTS

        http.formLogin()
        .usernameParameter("email")
        .passwordParameter("password")
        .loginPage("/index");

        http.logout().logoutUrl("/logout");
        

    }
    
    

}
