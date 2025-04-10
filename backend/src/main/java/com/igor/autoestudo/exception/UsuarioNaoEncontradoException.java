package com.igor.autoestudo.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(Long id) {
        super("Usuário com ID " + id + " não encontrado.");
    }
}
