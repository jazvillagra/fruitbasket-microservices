package com.example.cherry.service.impl;

import com.example.cherry.controller.ApiController;
import com.example.cherry.dto.PersonaDTO;
import com.example.cherry.service.PersonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RefreshScope
public class PersonaServiceImpl implements PersonaService {
    Logger LOGGER = LoggerFactory.getLogger(ApiController.class);
    @Override
    public PersonaDTO savePersona(String id, String nombres,
                                  String apellidos, MultipartFile imagen) throws Exception{

        // Validación
        if (id.length() < 1 || id.length() > 10) {
            LOGGER.error("El id del objeto debe tener una longitud entre 1 y 10 caracteres. Id Recibido: " + id);
            throw new Exception("El id del objeto debe tener una longitud entre 1 y 10 caracteres");
        }
        if (nombres.length() > 100 || nombres.length() < 1) {
            LOGGER.error("El campo nombres debe tener una longitud entre 1 y 10 caracteres. " +
                    "Nombres Recibidos: "+ nombres);
            throw new Exception("El campo nombres debe tener una longitud entre 1 y 100 caracteres");
        }
        if (apellidos.length() > 200 || apellidos.length() < 1) {
            LOGGER.error("El campo apellidos debe tener una longitud entre 1 y 200 caracteres. " +
                    "Apellidos recibidos: " + apellidos);
            throw new Exception("El campo 'apellidos' debe tener entre 1 y 200 caracteres");
        }
        if(imagen.getSize() > 51200) {
            LOGGER.error("La imagen no puede superar los 500KB en tamaño." +
                    "Tamaño de imagen recibida: "+ imagen.getSize() + "bytes");
            throw new Exception("La imagen no puede superar los 500KB en tamaño");
        }
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setId(id);
        personaDTO.setNombres(nombres);
        personaDTO.setApellidos(apellidos);
        personaDTO.setImagen(imagen);

        return personaDTO;
    }
}
