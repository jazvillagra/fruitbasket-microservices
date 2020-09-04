package com.example.watermelon.controller;

import com.example.watermelon.constants.ApiPaths;
import com.example.watermelon.dto.PersonaDTO;
import com.example.watermelon.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.MalformedParametersException;

@RestController
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping(ApiPaths.PERSONA_BY_ID)
    public PersonaDTO getPersona(@PathVariable(value = "id") String id)
            throws MalformedParametersException, InterruptedException{
        if (id == null || id.length() > 10 || id.length() < 1) {
            throw new MalformedParametersException("Campo 'id' debe tener una longitud entre 1 y 10 caracteres");
        }
        return personaService.getPersonaById(id);
    }

}
