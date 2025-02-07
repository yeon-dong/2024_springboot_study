package com.hyundaiautoever.beexample.infrastructure.config.auditing;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if  (authentication != null) {
                Object principal = authentication.getPrincipal();
                Jwt jwt = (Jwt)principal;
                return Optional.of(jwt.getClaimAsString("sub"));
            }
            return Optional.empty();
        };
    }
}
