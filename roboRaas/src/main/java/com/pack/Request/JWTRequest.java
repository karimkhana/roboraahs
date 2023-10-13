package com.pack.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class JWTRequest {
	
	private String Email;
	
	private String Password;

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public JWTRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JWTRequest(String email, String password) {
		super();
		Email = email;
		Password = password;
	}

	@Override
	public String toString() {
		return "JWTRequest [Email=" + Email + ", Password=" + Password + "]";
	}



}
