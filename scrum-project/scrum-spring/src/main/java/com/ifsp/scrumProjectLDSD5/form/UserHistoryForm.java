package com.ifsp.scrumProjectLDSD5.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserHistoryForm {

	private Long id;
	@NotNull
	@NotBlank
	private String title;
	private Long userId;
	private Long reporterId;
	private String description;
}
