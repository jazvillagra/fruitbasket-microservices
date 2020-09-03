package com.example.cherry.service;

import com.example.cherry.dto.PersonaDTO;
import org.springframework.web.multipart.MultipartFile;

public interface PersonaService {
    PersonaDTO savePersona(String id, String nombres, String apellidos, MultipartFile imagen) throws Exception;
}
