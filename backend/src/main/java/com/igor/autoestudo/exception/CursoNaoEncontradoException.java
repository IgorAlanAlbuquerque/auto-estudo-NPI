package com.igor.autoestudo.exception;

public class CursoNaoEncontradoException extends RuntimeException {
    public CursoNaoEncontradoException(Long id) {
        super("Curso com ID " + id + " não encontrado.");
    }
}
