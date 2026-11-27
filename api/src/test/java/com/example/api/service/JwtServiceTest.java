package com.example.api.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.api.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.jwt.JwtException;

class JwtServiceTest {
    @Test
    void emiteTokenValidadoComIdentificadorDoUsuario() {
        var config = new SecurityConfig();
        var key = config.jwtSecretKey("chave-apenas-para-testes-com-32-bytes");
        var token = new JwtService(config.jwtEncoder(key), 60).emitir(42);
        var jwt = config.jwtDecoder(key).decode(token);
        assertEquals("42", jwt.getSubject());
        assertEquals(JwtService.ISSUER, jwt.getClaimAsString("iss"));
        assertNotNull(jwt.getExpiresAt());
        var outraChave = config.jwtSecretKey("outra-chave-apenas-para-testes-32-bytes");
        assertThrows(JwtException.class, () -> config.jwtDecoder(outraChave).decode(token));
    }

    @Test
    void rejeitaChaveCurta() {
        assertThrows(IllegalArgumentException.class, () -> new SecurityConfig().jwtSecretKey("curta"));
    }
}
