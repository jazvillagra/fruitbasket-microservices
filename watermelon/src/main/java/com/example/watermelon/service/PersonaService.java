package com.example.watermelon.service;

import com.example.watermelon.dto.PersonaDTO;

import java.lang.reflect.MalformedParametersException;

public interface PersonaService {

    PersonaDTO getPersonaById(String id) throws MalformedParametersException, InterruptedException;

}
