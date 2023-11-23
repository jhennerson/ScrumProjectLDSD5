package com.ifsp.scrumProjectLDSD5.exception;

import java.io.Serial;

public class UserAlreadyExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public UserAlreadyExistsException(String username) {
        super("Já existe um usuário cadastrado com o username: " + username);
    }
}
