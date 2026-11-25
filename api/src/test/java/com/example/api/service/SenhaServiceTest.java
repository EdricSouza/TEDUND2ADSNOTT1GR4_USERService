package com.example.api.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SenhaServiceTest {

    private final SenhaService senhaService = new SenhaService();

    @Test
    void geraHashEVerificaSenha() {
        String hash = senhaService.gerarHash("senha-de-teste");

        assertNotEquals("senha-de-teste", hash);
        assertTrue(senhaService.verificar("senha-de-teste", hash));
        assertFalse(senhaService.verificar("outra-senha", hash));
    }
}
