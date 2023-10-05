package com.ifsp.scrumProjectLDSD5.form;

import com.ifsp.scrumProjectLDSD5.entity.User;
import com.ifsp.scrumProjectLDSD5.entity.UserStory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


public class UserStoryForm {

	private Long id;
	@NotNull
	@NotBlank
	private String title;
	private Long userId;
	private Long reporterId;
	private String description;
	
    public UserStory toEntity() {
    	
        UserStory userStory = new UserStory();
        userStory.setId(this.id);
        userStory.setTitle(this.title);
        userStory.setDescription(this.description);
        return userStory;
    }
    
    public UserStory toEntity(User user,User reporter) {
        UserStory userStory = new UserStory();
        userStory.setTitle(this.title);
        userStory.setAssignee(user);
        userStory.setReporter(reporter);
        userStory.setDescription(this.description);
        return userStory;
    }
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getReporterId() {
		return reporterId;
	}
	public void setReporterId(Long reporterId) {
		this.reporterId = reporterId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
}
