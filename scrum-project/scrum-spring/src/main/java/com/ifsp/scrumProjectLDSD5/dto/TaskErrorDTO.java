package com.ifsp.scrumProjectLDSD5.dto;

import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ifsp.scrumProjectLDSD5.interfaces.ITask;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class TaskErrorDTO implements ITask {
	private OffsetDateTime timeStamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	
	private String trace;
	

	static public TaskErrorDTO set(HttpStatus status,String message,String path) {
		
		TaskErrorDTO taskErrorDTO = new TaskErrorDTO();
		taskErrorDTO.setTimeStamp(OffsetDateTime.now());
		taskErrorDTO.setStatus(Integer.valueOf(status.value()));
		taskErrorDTO.setError(status.getReasonPhrase());
		taskErrorDTO.setMessage(message);
		taskErrorDTO.setPath(path);
		return taskErrorDTO;
	}
	
	static public TaskErrorDTO set(HttpStatus status,String message,String path,String trace) {
		
		TaskErrorDTO taskErrorDTO = new TaskErrorDTO();
		taskErrorDTO.setTimeStamp(OffsetDateTime.now());
		taskErrorDTO.setStatus(Integer.valueOf(status.value()));
		taskErrorDTO.setError(status.getReasonPhrase());
		taskErrorDTO.setMessage(message);
		taskErrorDTO.setPath(path);
		taskErrorDTO.setTrace(trace);
		return taskErrorDTO;
	}
	
    public ResponseEntity<TaskErrorDTO> toResponseEntity() {
        return ResponseEntity.status(this.status).body(this);
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
	public void setStatus(Integer integer) {
		this.status = integer;
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
	
	public String getTrace() {
		return trace;
	}

	public void setTrace(String trace) {
		this.trace = trace;
	}
	
	
	
}
