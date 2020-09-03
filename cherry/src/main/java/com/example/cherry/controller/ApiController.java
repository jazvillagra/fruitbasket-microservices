package com.example.cherry.controller;

import com.example.cherry.constants.ApiPaths;
import com.example.cherry.dto.PersonaDTO;
import com.example.cherry.service.PersonaService;
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
    @Autowired
    private PersonaService personaService;

    Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @RequestMapping(ApiPaths.SERVICE_INSTANCES_BY_APPLICATION_NAME)
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    /**
     * Registra a una persona. Devuelve objeto construido con datos enviados como parámetros
     * @param id
     * @param nombres
     * @param apellidos
     * @param imagen
     * @return
     * @throws Exception
     */
    @PostMapping(ApiPaths.REGISTRAR_PERSONA)
    public PersonaDTO registrarPersona(@RequestParam String id,
                                       @RequestParam String nombres,
                                       @RequestParam String apellidos,
                                       @RequestParam MultipartFile imagen) throws Exception {

        PersonaDTO persona = personaService.savePersona(id, nombres, apellidos, imagen);

        // Se imprimen el objeto construido ya que no hay persistencia de datos
        LOGGER.info("Se registró a la persona: " + persona.toString());

        return (persona);
    }
}
