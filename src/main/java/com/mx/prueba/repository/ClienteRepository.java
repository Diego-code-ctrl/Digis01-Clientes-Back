package com.mx.prueba.repository;

import com.mx.prueba.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author diegobecerril
 */
public interface ClienteRepository extends JpaRepository<Clientes, Long>{
    
    public Clientes findByNombre(String nombre);
    public Clientes findByApellidoPaterno(String apellidoPaterno);
    public Clientes findByCorreo(String correo);
}
