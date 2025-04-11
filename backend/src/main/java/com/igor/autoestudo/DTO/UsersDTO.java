package com.igor.autoestudo.DTO;

import com.igor.autoestudo.entity.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsersDTO {
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String password;

    private Boolean ativo = true;

    public Users toEntity() {
        Users user = new Users();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setAtivo(this.ativo);
        return user;
    }
}
