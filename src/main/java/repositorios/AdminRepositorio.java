/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import com.clinica.sgt.entidades.Admin;
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
public interface AdminRepositorio extends JpaRepository<Admin,String>{
 @Query("SELECT a FROM Admin a WHERE a.alta = 1 ORDER BY a.nombre ASC")
    public List<Admin> mostrarAdministradoresActivos(); 
    
     @Query("SELECT a FROM Admin a ORDER BY a.nombre ASC")
    public List<Admin> mostrarTodosAdministradores();
}
