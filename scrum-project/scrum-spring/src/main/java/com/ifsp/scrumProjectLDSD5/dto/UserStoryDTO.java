package com.ifsp.scrumProjectLDSD5.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ifsp.scrumProjectLDSD5.interfaces.IUserStory;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class UserStoryDTO implements IUserStory {
	private Long id;
	private String title;
	private UserDTO assignee;
	private UserDTO reporter;
	private String description;
	
	
	
}
