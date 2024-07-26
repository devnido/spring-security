package com.example.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.controllers.dto.AuthLoginRequest;
import com.example.security.controllers.dto.AuthRegisterRequest;
import com.example.security.controllers.dto.AuthResponse;
import com.example.security.service.UserDetailServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@PreAuthorize("permitAll()")
public class AuthenticationController {

  @Autowired
  private UserDetailServiceImpl userDetailService;

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRegisterRequest userRequest) {
    return new ResponseEntity<>(this.userDetailService.registerUser(userRequest), HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthLoginRequest userRequest) {
    return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
  }

}
