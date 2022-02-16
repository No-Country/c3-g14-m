//package com.clinica.sgt.servicios;
//
//import com.clinica.sgt.repositorios.UsuarioRepositorio;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService{
//    
//    @Autowired
//    private UsuarioRepositorio usuarioRepositorio;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return usuarioRepositorio.findByUsername(username);
//    }
//
//    
//}
