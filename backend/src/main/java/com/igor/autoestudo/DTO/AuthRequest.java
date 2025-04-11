package com.igor.autoestudo.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String senha;
}