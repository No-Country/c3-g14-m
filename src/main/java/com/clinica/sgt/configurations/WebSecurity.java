package com.clinica.sgt.configurations;

import com.clinica.sgt.servicios.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

   
    
    
    // PARA EVITAR PROBLEMAS CON THYMELEAF Y QUE NO SE APLIQUE LA SEGURIDAD A LOS RESOURCES
    String[] resources = new String[]{"/include/**", "/css/**", "/icons/**", "/img/**", "/js/**"
    };

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();   //Metodo para instanciar encoder
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); //Para mayor seguridad se encripta la password
        auth.inMemoryAuthentication()
                .withUser("admin@mail.com")
                .password(passwordEncoder().encode("1234"))
                .roles("ADMIN");
        
    }
    
   

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Restricciones al acceso dependiendo el rol del usuario
        http
            .authorizeRequests()
            .antMatchers("/css/**", "/js/**", "/img/**").permitAll();
        http.authorizeRequests()
        .antMatchers("/paciente/registro").permitAll()
        .antMatchers("/index").permitAll()
        .antMatchers("/logout").hasAnyRole("ADMIN", "PROFESIONAL", "PACIENTE")
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/paciente/**").hasAuthority("PACIENTE")
        .antMatchers("/profesional/**").hasAuthority("PROFESIONAL")
        .anyRequest().authenticated();
        
         //REVISAR SI NO FUNCIONA hasRole probar con hasAuthority
        //LUEGO AGREGAR LOS PROXIMOS ENDPOINTS

        http.formLogin()
        .usernameParameter("email")
        .passwordParameter("password")
        .loginPage("/login").permitAll()
        //.failureUrl("/error")
        .defaultSuccessUrl("/welcome") 
        .and()
        .csrf().disable();

        http.logout().logoutUrl("/logout").logoutSuccessUrl("/");
        


    }

}
