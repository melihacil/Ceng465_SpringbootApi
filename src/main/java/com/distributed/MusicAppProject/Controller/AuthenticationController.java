package com.distributed.MusicAppProject.Controller;

import com.distributed.MusicAppProject.DataObject.AuthenticationResponse;
import com.distributed.MusicAppProject.DataObject.Login_Request;
import com.distributed.MusicAppProject.DataObject.Register_Request;
import com.distributed.MusicAppProject.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;


    // register
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Register_Request registerRequest) {
        return ResponseEntity.ok(service.register(registerRequest));
    }

    // login
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody Login_Request loginRequest) {
        return ResponseEntity.ok(service.login(loginRequest));
    }
}