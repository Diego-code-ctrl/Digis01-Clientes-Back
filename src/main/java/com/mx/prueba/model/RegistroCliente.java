package com.mx.prueba.model;

/**
 *
 * @author diegobecerril
 */

public record RegistroCliente(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String direccion,
                              String telefono, String correo) { }
