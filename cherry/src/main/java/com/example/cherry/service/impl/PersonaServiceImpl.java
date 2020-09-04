package com.example.cherry.service.impl;

import com.example.cherry.controller.ApiController;
import com.example.cherry.dto.PersonaDTO;
import com.example.cherry.service.PersonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PersonaServiceImpl implements PersonaService {
    Logger LOGGER = LoggerFactory.getLogger(ApiController.class);
    @Override
    public PersonaDTO savePersona(String id, String nombres,
                                  String apellidos, MultipartFile imagen) throws Exception{

        // Validación
        if (id.length() < 1 || id.length() > 10) {
            LOGGER.error("El id del objeto debe tener una longitud entre 1 y 10 caracteres." +
                    "Id Recibido: %s", id);
            throw new Exception("El id del objeto debe tener una longitud entre 1 y 10 caracteres");
        }
        if (nombres.length() > 100 || nombres.length() < 1) {
            LOGGER.error("El campo nombres debe tener una longitud entre 1 y 10 caracteres. " +
                    "Nombres Recibidos: %s", nombres);
            throw new Exception("El campo nombres debe tener una longitud entre 1 y 100 caracteres");
        }
        if (apellidos.length() > 200 || apellidos.length() < 1) {
            LOGGER.error("El campo apellidos debe tener una longitud entre 1 y 200 caracteres. " +
                    "Apellidos recibidos: %s", apellidos);
            throw new Exception("El campo 'apellidos' debe tener entre 1 y 200 caracteres");
        }
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setId(id);
        personaDTO.setNombres(nombres);
        personaDTO.setApellidos(apellidos);
        System.out.println("------------------ seteando imagen....");
        personaDTO.setImagen(imagen);
        System.out.println("------------------- imagen seteada a dto");

        return personaDTO;
    }
}
