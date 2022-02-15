package com.clinica.sgt.repositorios;

import com.clinica.sgt.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String>{
    @Query("SELECT u FROM Usuario u WHERE u.alta = 1 ORDER BY u.nombre ASC")
    public List<Usuario> mostrarUsuariosActivos();
    
    @Query("SELECT u FROM Usuario u ORDER BY u.nombre ASC")
    public List<Usuario> mostrarTodosUsuarios();
    
    @Query("SELECT u FROM Usuario u WHERE u.mail = :nombre") //Al ser el mail el username directamente comparamos el parametro nombre con
    public Usuario findByUsername(@Param("id")String nombre);// el atributo mail 
    
    @Query("SELECT u FROM Usuario u WHERE u.id = :id")
    public Usuario buscarPorID(@Param("id")String id);
    
    @Query("SELECT u FROM Usuario u WHERE u.dni = :dni")
    public Usuario buscarPorDNI(@Param("dni")String dni);
}
