package com.example.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import com.example.api.entity.Perfil;
import com.example.api.repository.PerfilRepository;

@Service 
public class PerfilService {

    @Autowired 
    private PerfilRepository perfilRepository;

    public Perfil getPerfilById(Long id) {
        if (id == null || id <= 0) {
            throw new NoSuchElementException("Perfil não encontrado");
        }
        return perfilRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Perfil não encontrado"));
    }
}
