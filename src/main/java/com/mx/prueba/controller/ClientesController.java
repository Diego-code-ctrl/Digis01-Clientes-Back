package com.mx.prueba.controller;

import com.mx.prueba.entity.Clientes;
import com.mx.prueba.repository.ClienteRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author diegobecerril
 */
@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping(value = "/agregarCliente")
    public final ResponseEntity agregarCliente(@RequestBody Clientes cliente) {
        clienteRepository.save(cliente);
        return ResponseEntity.ok("Cliente agregado");
    }

    @PostMapping(value = "/cargaMasiva")
    public final ResponseEntity cargaMasiva(@RequestParam("archivo") MultipartFile archivo) throws IOException {
        List<Clientes> clientesCargados = new ArrayList();
        List<String> errores = new ArrayList();

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(archivo.getInputStream()))) {
            String linea;
            int numeroDeLinea = 0;

            while ((linea = bf.readLine()) != null) {
                numeroDeLinea++;
                try {
                    String[] datos = linea.split("\\|");

                    if (datos.length < 6) {
                        errores.add("Línea " + numeroDeLinea + " inválida: " + linea);
                        continue;
                    }

                    Clientes cliente = new Clientes();
                    cliente.setNombre(datos[0].trim());
                    cliente.setApellidoPaterno(datos[1].trim());
                    cliente.setApellidoMaterno(datos[2].trim());
                    cliente.setDireccion(datos[3].trim());
                    cliente.setTelefono(datos[4].trim());
                    cliente.setCorreo(datos[5].trim());

                    clientesCargados.add(cliente);
                } catch (Exception e) {
                    errores.add("Error procesando línea " + numeroDeLinea + ": " + e.getMessage());
                }
            }

            clienteRepository.saveAll(clientesCargados);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar el archivo: " + e.getMessage());
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Clientes agregados exitosamente: ").append(clientesCargados.size()).append("\n");

        if (!errores.isEmpty()) {
            sb.append("Errores encontrados: ").append(errores.size()).append("\n");
            errores.forEach(error -> sb.append(error).append("\n"));
        }

        return ResponseEntity.ok(sb.toString());
    }

    @GetMapping(value = "/obtenerCliente")
    public final ResponseEntity<Clientes> obtenerCliente(@RequestParam String nombre) {
        Clientes cliente = clienteRepository.findByNombre(nombre);

        return ResponseEntity.ok(cliente);
    }

    @GetMapping(value = "/obtenerClientes")
    public final ResponseEntity<List<Clientes>> obtenerClientes() {
        List<Clientes> clientes = clienteRepository.findAll();

        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(clientes);
    }
}
