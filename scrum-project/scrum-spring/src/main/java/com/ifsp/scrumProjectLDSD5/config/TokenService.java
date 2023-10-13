package com.ifsp.scrumProjectLDSD5.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ifsp.scrumProjectLDSD5.entity.User;

@Service
public class TokenService {
	
	
	@Value("${api.security.token.secret}")
	private String secret;

	public String generateToken(User user) {
		try {
			Algorithm algo = Algorithm.HMAC256(secret);
			String token = JWT.create()
					.withIssuer("Auth-api")
					.withSubject(user.getUsername())
					.withExpiresAt(genExpirationDate())
					.sign(algo);
			
			return token;
			
		}catch(JWTCreationException e) {
			throw new RuntimeException("Errow while generating token",e);
		}
		

	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("Auth-api")
					.build()
					.verify(token)
					.getSubject();
		}catch(JWTVerificationException e) {
			e.printStackTrace();
			return "";
			
		}
	}
	
	private Instant genExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
	
}