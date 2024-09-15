package com.mbb.assessment.dto.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private Long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String gender;

    private String image;

    private String token;

    private String refreshToken;
}
