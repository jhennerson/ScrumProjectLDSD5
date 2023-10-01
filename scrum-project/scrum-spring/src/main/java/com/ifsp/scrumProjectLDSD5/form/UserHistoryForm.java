package com.ifsp.scrumProjectLDSD5.form;

import com.ifsp.scrumProjectLDSD5.entity.User;
import com.ifsp.scrumProjectLDSD5.entity.UserHistory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


public class UserHistoryForm {

	private Long id;
	@NotNull
	@NotBlank
	private String title;
	private Long userId;
	private Long reporterId;
	private String description;
	
    public UserHistory toEntity() {
    	
        UserHistory userHistory = new UserHistory();
        userHistory.setId(this.id);
        userHistory.setTitle(this.title);
        userHistory.setDescription(this.description);
        return userHistory;
    }
    
    public UserHistory toEntity(User user,User reporter) {
        UserHistory userHistory = new UserHistory();
        userHistory.setTitle(this.title);
        userHistory.setUser(user);
        userHistory.setReporter(reporter);
        userHistory.setDescription(this.description);
        return userHistory;
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
