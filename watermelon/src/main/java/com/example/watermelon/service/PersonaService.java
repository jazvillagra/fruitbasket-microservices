package com.example.watermelon.service;

import java.lang.reflect.MalformedParametersException;

public interface PersonaService {

    String getPersonaById(String id) throws MalformedParametersException, InterruptedException;

}
