package com.ifsp.scrumProjectLDSD5.exception;

public class EmptyRecordException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    private Long id;

    public EmptyRecordException(Long id) {
        this.id = id;
    }
    public EmptyRecordException() {
        
    }

	public Long getId() {
		return id;
	}
}
