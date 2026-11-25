package com.example.api.service;

import java.time.Instant;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    public static final String ISSUER = "clinica-backend";
    private final JwtEncoder encoder;
    private final long validadeMinutos;

    public JwtService(JwtEncoder encoder, @Value("${app.auth.jwt-validade-minutos:60}") long validadeMinutos) {
        if (validadeMinutos < 1 || validadeMinutos > 1440) {
            throw new IllegalArgumentException("JWT_VALIDIDADE_MINUTOS deve estar entre 1 e 1440.");
        }
        this.encoder = encoder;
        this.validadeMinutos = validadeMinutos;
    }

    public String emitir(long usuarioId) {
        Instant agora = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(ISSUER).subject(Long.toString(usuarioId))
                .issuedAt(agora).expiresAt(agora.plusSeconds(validadeMinutos * 60))
                .id(UUID.randomUUID().toString()).build();
        return encoder.encode(JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).type("JWT").build(), claims)).getTokenValue();
    }
}
