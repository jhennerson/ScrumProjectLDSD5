package com.ifsp.scrumProjectLDSD5.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@Table(name="USER")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false)
    private String email;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
    
    
}
