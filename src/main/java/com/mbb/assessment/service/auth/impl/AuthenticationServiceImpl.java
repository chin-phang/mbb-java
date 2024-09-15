package com.mbb.assessment.service.auth.impl;

import com.mbb.assessment.config.ApplicationProperties;
import com.mbb.assessment.dto.auth.LoginRequest;
import com.mbb.assessment.dto.auth.LoginResponse;
import com.mbb.assessment.dto.external.AuthResponse;
import com.mbb.assessment.exception.AuthNullPointerException;
import com.mbb.assessment.exception.AuthIllegalArgumentException;
import com.mbb.assessment.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final ApplicationProperties applicationProperties;

    private final RestTemplate restTemplate;

    @Override
    public LoginResponse authenticate(LoginRequest request) throws AuthNullPointerException, AuthIllegalArgumentException {
        try {
            AuthResponse result = restTemplate.postForObject(applicationProperties.getExternalApi().getAuth(), request, AuthResponse.class);

            if (result == null) {
                String errMsg = String.format("Response from %s is null", applicationProperties.getExternalApi().getAuth());
                log.error("authenticate - message : {}", errMsg);
                throw new AuthNullPointerException(errMsg);
            }

            return LoginResponse.builder()
                    .token(result.getToken())
                    .build();
        } catch (Exception ex) {
            throw new AuthIllegalArgumentException(ex.getMessage());
        }
    }
}
