package com.example.watermelon.service.impl;

import com.example.watermelon.constants.AppConstants;
import com.example.watermelon.controller.PersonaController;
import com.example.watermelon.dto.PersonaDTO;
import com.example.watermelon.service.PersonaService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.lang.reflect.MalformedParametersException;

@Service
@RefreshScope
public class PersonaServiceImpl implements PersonaService {

    @Value("${timeoutMessage:No se pudo obtener datos del kiwi}")
    private String timeoutMsg;

    @Value("${waitTime:0}")
    private Long waitSecs;

    private Logger LOGGER = LoggerFactory.getLogger(PersonaController.class);

    @Override
    @HystrixCommand(fallbackMethod = "fallbackGetPersona", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = AppConstants.MAX_TOLERANCIA_SEC)
    })
    public String getPersonaById(String id) throws MalformedParametersException, InterruptedException {

        try {
            if (waitSecs != null && waitSecs > 0) {
                LOGGER.info("Segundos a esperar: {}", waitSecs);
                Thread.sleep(waitSecs);
            }
        } catch (Exception e){
            throw new InterruptedException();
        }
        //Datos de prueba
        PersonaDTO personaDTO = new PersonaDTO();
        if (id == null || id.length() > 10 || id.length() < 1) {
            LOGGER.error("Campo 'id' debe tener una longitud entre 1 y 10 caracteres");
            throw new MalformedParametersException("Campo 'id' debe tener una longitud entre 1 y 10 caracteres");
        }
        personaDTO.setId(id);
        personaDTO.setApellidos("street");
        personaDTO.setNombres("cornelia");
        personaDTO.setImagen(null);

        return personaDTO.toString();
    }
    public String fallbackObtenerPersona(String id) {
        return timeoutMsg;
    }

}
