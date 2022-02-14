/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import com.clinica.sgt.entidades.Paciente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cristian
 */
@Repository
public interface PacienteRepositorio extends JpaRepository<Paciente,String>{
 @Query("SELECT p FROM Paciente p WHERE p.alta = 1 ORDER BY p.nombre ASC")
    public List<Paciente> mostrarPacientesActivos(); 
    
     @Query("SELECT p FROM Paciente p ORDER BY p.nombre ASC")
    public List<Paciente> mostrarTodosPacientes();
}