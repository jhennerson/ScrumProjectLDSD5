package com.ifsp.scrumProjectLDSD5.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "USERHISTORY")
public class UserHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "BIGINT")
	private Long id;
	private String title;
	@ManyToOne
	private User user;
	@ManyToOne
	private User reporter;
	private String description;
	
	
	
	
	
}
