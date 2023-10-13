package com.pack.controller;

import java.util.List;

import org.slf4j.Logger;

import com.pack.entityss.ChatbotRequest;
import com.pack.entityss.register;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.pack.JwtHelper.JwtHelper;
import com.pack.Request.JWTRequest;
import com.pack.Request.JwtResponse;

import com.pack.respo.requestRespo;
import com.pack.respo.resgiterRespo;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private requestRespo requestRespo;
    
    @Autowired
    private resgiterRespo registerRespo; 
    
    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JWTRequest request) {
        // Your authentication logic
        this.doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = new JwtResponse(token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    
    
    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
    
	@PostMapping("/Register")
	public register register(@RequestBody register rite) {
		register rites = new register();

		rites.setName(rite.getName());
		rites.setEmail(rite.getEmail());
		rites.setPhoneNo(rite.getPhoneNo());
		rites.setPassword(rite.getPassword());
		rites.setAdmin(false);
		register ops= registerRespo.save(rites);
		return ops;
	}
	
	
	
	@PostMapping("videoRequest")
	public ChatbotRequest adminAdd(@RequestBody ChatbotRequest rite) {

		ChatbotRequest s=requestRespo.save(rite);
		
			
		return s;
	}
	
	@GetMapping("data")
	public List<ChatbotRequest> datas() {
		
		return (List<ChatbotRequest>)requestRespo.findAll();
	}
	
    
    
    

}

