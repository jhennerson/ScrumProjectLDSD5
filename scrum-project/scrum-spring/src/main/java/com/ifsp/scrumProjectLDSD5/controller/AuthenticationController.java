package com.ifsp.scrumProjectLDSD5.controller;

import com.ifsp.scrumProjectLDSD5.dto.AuthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifsp.scrumProjectLDSD5.config.TokenService;
import com.ifsp.scrumProjectLDSD5.dto.AuthenticationDTO;
import com.ifsp.scrumProjectLDSD5.dto.UserDTO;
import com.ifsp.scrumProjectLDSD5.entity.User;
import com.ifsp.scrumProjectLDSD5.repository.UserRepository;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository repository;
	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity login (@RequestBody @Validated AuthDTO userDTO) {
		try {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
			Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			String generateToken = tokenService.generateToken((User) authenticate.getPrincipal());
			return ResponseEntity.ok(generateToken);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Validated AuthDTO data ) {
 		if(this.repository.findByUsername(data.getUsername()) != null){
 			//ja existe alguem com esse username
			return null;
		}

		String encode = new BCryptPasswordEncoder().encode(data.getPassword());
		User user = new User(data.getUsername(),encode,data.getEmail());
		this.repository.save(user);
		return ResponseEntity.ok().build();
	}
}
