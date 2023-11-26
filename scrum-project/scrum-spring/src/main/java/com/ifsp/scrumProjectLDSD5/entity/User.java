package com.ifsp.scrumProjectLDSD5.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ifsp.scrumProjectLDSD5.enumeration.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({
		"deleted",
		"password",
		"role",
		"enabled",
		"authorities",
		"accountNonExpired",
		"credentialsNonExpired",
		"accountNonLocked",
		"memberProjects",
		"reporterProjects",
		"reporterSprints",
		"assigneeUserStories",
		"reporterUserStories",
		"assigneeTasks",
		"reporterTasks"
})
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class User implements UserDetails, Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
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

	@ManyToMany(mappedBy = "members")
	private List<Project> memberProjects = new ArrayList<>();

	@OneToMany(mappedBy = "reporter")
	private List<Project> reporterProjects = new ArrayList<>();

	@OneToMany(mappedBy = "reporter")
	private List<Sprint> reporterSprints = new ArrayList<>();

	@OneToMany(mappedBy = "assignee")
	private List<UserStory> assigneeUserStories = new ArrayList<>();

	@OneToMany(mappedBy = "reporter")
	private List<UserStory> reporterUserStories = new ArrayList<>();

	@OneToMany(mappedBy = "assignee")
	private List<Task> assigneeTasks = new ArrayList<>();

	@OneToMany(mappedBy = "reporter")
	private List<Task> reporterTasks = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private UserRole role = UserRole.USER;

	private Boolean deleted = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
