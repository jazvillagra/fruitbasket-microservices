package com.example.cherry.controller;

import com.example.cherry.constants.ApiPaths;
import com.example.cherry.dto.PersonaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RefreshScope
public class ApiController {

    @Autowired
    private DiscoveryClient discoveryClient;

    Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @RequestMapping(ApiPaths.SERVICE_INSTANCES_BY_APPLICATION_NAME)
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }
    /**
     * Registra una persona en el sistema y retorna el objeto de la Persona
     *
     * @param  id ID único
     * @param  nombres Nombres de la nueva persona
     * @param  apellidos Apellidos de la nueva persona
     * @param  imagen Imagen de un tamaño máximo de 500kB
     */
    @PostMapping(ApiPaths.REGISTRAR_PERSONA)
    public PersonaDTO registrarPersona(@RequestParam("id") String id,
                                       @RequestParam("nombres") String nombres,
                                       @RequestParam("apellidos") String apellidos,
                                       @RequestParam("imagen") MultipartFile imagen) throws Exception {
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setId(id);
        personaDTO.setNombres(nombres);
        personaDTO.setApellidos(apellidos);
        personaDTO.setImagen(imagen);

        // Validación
        if (personaDTO.getId().length() > 10 || personaDTO.getId().length() < 1) {
            throw new Exception("Campo 'id' debe tener una longitud entre 1 y 10 caracteres");
        }

        if (personaDTO.getNombres().length() > 100 || personaDTO.getNombres().length() < 1) {
            throw new Exception("Campo 'nombres' debe tener una longitud entre 1 y 100 caracteres");
        }

        if (personaDTO.getApellidos().length() > 200 || personaDTO.getApellidos().length() < 1) {
            throw new Exception("El campo 'apellidos' debe tener entre 1 y 200 caracteres");
        }

        // No se guarda nada en una BD por lo que solo imprimimos los datos pasados que se guardaron y ya
        LOGGER.info("Se registró a la persona: " + personaDTO.toString());

        return (personaDTO);
    }
}
