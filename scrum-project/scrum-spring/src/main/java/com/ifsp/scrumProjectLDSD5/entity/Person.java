package com.ifsp.scrumProjectLDSD5.entity;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "person")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@NotNull
	@Length(min = 3, max = 20)
	@Column(name = "username", nullable = false, length = 20)
	private String username;

	@NotBlank
	@NotNull
	@Column(name = "password", nullable = false)
	private String password;

	@NotBlank
	@NotNull
	@Column(name = "email", nullable = false)
	private String email;

	public Person() {}

	public Person(Person person) {
		this.id = person.id;
		this.username = person.username;
		this.password = person.password;
		this.email = person.email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
