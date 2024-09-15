package com.mbb.assessment.controller.api;

import com.mbb.assessment.dto.auth.LoginResponse;
import com.mbb.assessment.dto.auth.LoginRequest;
import com.mbb.assessment.dto.error.ApiErrorResponse;
import com.mbb.assessment.exception.AuthNullPointerException;
import com.mbb.assessment.exception.AuthIllegalArgumentException;
import com.mbb.assessment.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationRestController {

    private final AuthenticationService authenticationService;

    @ExceptionHandler(AuthNullPointerException.class)
    public ResponseEntity<ApiErrorResponse> handleNullPointerException(AuthNullPointerException ex) {
        ApiErrorResponse apiErrRes = ApiErrorResponse.builder()
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorMessage(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrRes);
    }

    @ExceptionHandler(AuthIllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(AuthIllegalArgumentException ex) {
        ApiErrorResponse apiErrRes = ApiErrorResponse.builder()
                .errorCode(HttpStatus.BAD_REQUEST)
                .errorMessage(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrRes);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws AuthNullPointerException, AuthIllegalArgumentException {
        log.info("login - request : {}", request);

        LoginResponse result = authenticationService.authenticate(request);

        log.info("login - response : {}", result);

        return ResponseEntity.ok().body(result);
    }
}
