package com.clinica.sgt.servicios;

import com.clinica.sgt.entidades.Usuario;
import com.clinica.sgt.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
   
   @Autowired
   private UsuarioRepositorio usuarioRepositorio;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario user = usuarioRepositorio.findByUsername(username);
    // System.out.println(user.getUsername());
    //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    // System.out.println(encoder.matches(encoder.encode("2222"), user.getPassword()));         
    // System.out.println(user.getAuthorities());
    if(user==null){
        throw new UsernameNotFoundException("Nombre de usuario o clave incorrecta");
    }
    return new User(user.getUsername(), user.getPassword(), user.getAuthorities());

    // return new User("rjulianbenjamin@gmail.com", passwordEncoder().encode("12345"), AuthorityUtils.createAuthorityList("ROLE_PACIENTE"));
   }

   
}