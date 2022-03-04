package com.clinica.sgt.servicios;

import com.clinica.sgt.entidades.Usuario;
import com.clinica.sgt.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
   
   @Autowired
   private UsuarioRepositorio usuarioRepositorio;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
       Usuario usuario = usuarioRepositorio.findByUsername(username);
        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();
                        
            GrantedAuthority p1 = new SimpleGrantedAuthority("Usuario_Registrado");
            permisos.add(p1);
 
            //Esto me permite guardar el OBJETO USUARIO LOG, para luego ser utilizado
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);

            User user = new User(usuario.getMail(), usuario.getPassword(), permisos);
            return user;

        } else {
            return null;
        }

        //return usuarioRepositorio.findByUsername(username);
   }

   
}