package com.ifsp.scrumProjectLDSD5.dto;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;



@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ExceptionDTO {
	private OffsetDateTime timeStamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	
	
	
    public void setCamposComoNulo() {
    	setError(null);
    	setStatus(null);
    	setMessage(null);
    	setPath(null);
    	setTimeStamp(null);
    }
	
	public ExceptionDTO() {
		this.timeStamp = OffsetDateTime.now();
	}
	public OffsetDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(OffsetDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
