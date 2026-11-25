package com.example.api.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SenhaService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String gerarHash(String senha) {
        return encoder.encode(senha);
    }

    public boolean verificar(String senhaDigitada, String hashSalvo) {
        return encoder.matches(senhaDigitada, hashSalvo);
    }
}
