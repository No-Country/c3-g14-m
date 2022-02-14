/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import com.clinica.sgt.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cristian
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String>{
 @Query("SELECT u FROM Usuario u WHERE u.alta = 1 ORDER BY u.nombre ASC")
    public List<Usuario> mostrarUsuariosActivos(); 
    
     @Query("SELECT u FROM Usuario u ORDER BY u.nombre ASC")
    public List<Usuario> mostrarTodosUsuarios();
}

