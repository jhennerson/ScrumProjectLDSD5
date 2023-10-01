package com.ifsp.scrumProjectLDSD5.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Long id;

    public UsuarioNaoEncontradoException(Long id) {
        this.id = id;
    }
    public UsuarioNaoEncontradoException() {
        
    }

	public Long getId() {
		return id;
	}

}