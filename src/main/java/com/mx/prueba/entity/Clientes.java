package com.mx.prueba.entity;

import com.mx.prueba.model.RegistroCliente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author diegobecerril
 */
@Entity
@Table(name="Clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Clientes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
            
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;
    
    @Column(name = "apellidoPaterno", length = 50, nullable = false)
    private String apellidoPaterno;
    
    @Column(name = "apellidoMaterno", length = 50, nullable = false)
    private String apellidoMaterno;
    
    @Column(name = "direccion", length = 200, nullable = false)
    private String direccion;
    
    @Column(name = "telefono", length = 10, nullable = false)
    private String telefono;
    
    @Column(name = "correo", length = 100, nullable = false)
    private String correo;
    
    public Clientes(RegistroCliente registroCliente) {
        this.id = registroCliente.id();
        this.nombre = registroCliente.nombre();
        this.apellidoPaterno = registroCliente.apellidoPaterno();
        this.apellidoMaterno = registroCliente.apellidoMaterno();
        this.direccion = registroCliente.direccion();
        this.telefono = registroCliente.telefono();
        this.correo = registroCliente.correo();
    }
}
