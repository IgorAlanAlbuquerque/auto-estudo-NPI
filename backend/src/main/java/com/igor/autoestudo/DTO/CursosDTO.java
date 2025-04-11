package com.igor.autoestudo.DTO;

import com.igor.autoestudo.entity.Cursos;
import com.igor.autoestudo.entity.Turno;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CursosDTO {
    @NotBlank
    String name;

    @NotBlank
    @Size(min = 2, max = 2, message = "A sigla deve ter exatamente 2 caracteres.")
    String sigla;

    @NotBlank
    Turno turno;

    public Cursos toEntity() {
        Cursos curso = new Cursos();
        curso.setName(this.name);
        curso.setSigla(this.sigla);
        curso.setTurno(this.turno);
        return curso;
    }
}
