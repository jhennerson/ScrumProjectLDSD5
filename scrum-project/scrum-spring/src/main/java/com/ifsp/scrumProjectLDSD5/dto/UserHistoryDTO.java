package com.ifsp.scrumProjectLDSD5.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ifsp.scrumProjectLDSD5.interfaces.IUserHistory;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class UserHistoryDTO implements IUserHistory {
	private Long id;
	private String title;
	private UserDTO user;
	private UserDTO reporter;
	private String description;
	
	
	
}
