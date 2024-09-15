package com.mbb.assessment.service.auth;


import com.mbb.assessment.dto.auth.LoginRequest;
import com.mbb.assessment.dto.auth.LoginResponse;
import com.mbb.assessment.exception.AuthNullPointerException;
import com.mbb.assessment.exception.AuthIllegalArgumentException;

public interface AuthenticationService {

    LoginResponse authenticate(LoginRequest request) throws AuthNullPointerException, AuthIllegalArgumentException;
}
