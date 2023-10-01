package com.ifsp.scrumProjectLDSD5.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ifsp.scrumProjectLDSD5.dto.ExceptionDTO;
import com.ifsp.scrumProjectLDSD5.exception.RecordNotFoundException;
import com.ifsp.scrumProjectLDSD5.exception.UsuarioNaoEncontradoException;
import com.ifsp.scrumProjectLDSD5.filter.RequestPathFilter;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RecordNotFoundException ex) {
        return ex.getMessage();
    }
    
    
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionDTO> usuarioNaoEncontrado(UsuarioNaoEncontradoException e){
    	ExceptionDTO exception = new ExceptionDTO();
    	exception.setStatus(Integer.valueOf(HttpStatus.NOT_FOUND.value()));
    	exception.setPath(RequestPathFilter.getRequestPath());
    	exception.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
    	
    	if(e.getId() == null) {
    		exception.setMessage("Usuario não encontrado");    		
    	}else {
    		exception.setMessage("Usuario de ID " + e.getId() + " não foi encontrado");
    	}
    	
    	
    	alterarMensagemAleatoriamente(exception);
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
    }
    
    private static void alterarMensagemAleatoriamente(ExceptionDTO exceptionDTO) {
        int randomNumber = new Random().nextInt(1000) + 1;
        if (randomNumber <= 1) {
        	exceptionDTO.setCamposComoNulo();
            exceptionDTO.setMessage("Eu sabo");
            exceptionDTO.setPath("https://imgur.com/a/58qqgQO");
        }
    }
    
    

}
