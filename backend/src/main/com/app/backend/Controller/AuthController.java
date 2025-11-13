package com.app.backend.controller;

import com.app.backend.dto.loginRequest;
import com.app.backend.dto.loginResponse;
import com.app.backend.model.User;
import com.app.backend.repository.UserRepository;
import com.app.backend.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authentication;
import org.springframework.security.core.context.security.SecurityContextHolder;
import org.springframework.web.bin.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origin = "*")
public class AuthController{

    @Autowired
        private AuthenticationManager authenticationManager;

    @Autowired
        private JwtTokenProvider JwtTokenProvider;
    
    @Autowired
        private UserRepository userRepository;
    
    @PostMappig(value = "/Login", consumes = "application/json", produces = "application/json")
        public ResponseEntity<?> Login(@RequestBody LoginRequest loginRequest){
            try{
                Authentication authentication = authenticationManager.authenticate(
                    new 
                    UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword();
                    )
                )
                
                SecurityContextHolder.getContext().setAuthentication(authentication);

                String jwt = JwtTokenProvider.generateToken(authentication);

                User user = UserRepository,findbyUsername(loginRequest.getUsername())
                            .orElseThrow(() -> new RuntimeException("Usuario No encntrado bro"));
                
                return ResponseEntity.ok(new loginResponse(jwt, user));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("{\"Error\": \"Credenciales Invalidas\"}");
            }
        }

}