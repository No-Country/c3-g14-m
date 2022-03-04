package com.clinica.sgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SgtApplication {
    
//    @Autowired
//    private PacienteServicio pacienteServicio;

	public static void main(String[] args) {
		SpringApplication.run(SgtApplication.class, args);
	}
        
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        auth
//                .userDetailsService(pacienteServicio)
//                .passwordEncoder(new BCryptPasswordEncoder());
//    }

}
