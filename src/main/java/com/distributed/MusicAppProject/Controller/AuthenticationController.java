package com.distributed.MusicAppProject.Controller;

//import com.distributed.MusicAppProject.DataObject.AuthenticationResponse;

import api.deezer.exceptions.DeezerException;
import com.distributed.MusicAppProject.DataObject.Requests.ApiAuth_Request;
import com.distributed.MusicAppProject.DataObject.Requests.Login_Request;
import com.distributed.MusicAppProject.DataObject.Requests.Register_Request;
import com.distributed.MusicAppProject.DataObject.Response;
import com.distributed.MusicAppProject.DataObject.Requests.Update_Request;
import com.distributed.MusicAppProject.Services.AuthenticationService;
import com.distributed.MusicAppProject.Services.Interfaces.APIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService _service;
    private final APIService apiService;

    // register
    @PostMapping("/register")
    public Response<Object> register(@RequestBody Register_Request registerRequest) {
        Response response = _service.register(registerRequest);
        if (response == null) {
            return Response
                    .builder()
                    .status(false)
                    ._message("User already exists")
                    ._payload(null)
                    .build();
        }
        return Response
                .builder()
                .status(true)
                ._message("User created")
                ._payload(response)
                .build();
    }

    // login
    @PostMapping("/login")
    public Response<Object> login(@RequestBody Login_Request loginRequest) {
        String response = _service.login(loginRequest);
        if (response == null) {
            return Response
                    .builder()
                    .status(false)
                    ._message("User not found")
                    ._payload(null)
                    .build();
        }
        return Response
                .builder()
                .status(true)
                ._message("User logged in")
                ._payload(response)
                .build();
    }
    @PostMapping("/apiAuthenticate")
    public Response<Object> apiAuthenticate(@RequestBody ApiAuth_Request apiRequest) {
        //String response = _service.login(loginRequest);
        String response = apiService.authenticate(apiRequest);
        if (response == null) {
            return Response
                    .builder()
                    .status(false)
                    ._message("Api code NOT CORRECT!")
                    ._payload(null)
                    .build();
        }
        return Response
                .builder()
                .status(true)
                ._message("Api code correct, api User logged in")
                ._payload(response)
                .build();
    }
    @GetMapping("/apiCodeLink")
    public Response<Object> apiCodeLink() {
        //String response = _service.login(loginRequest);
        String response;
        try {
           response = apiService.getCodeLink();
        }
        catch (DeezerException e) {
            response = null;
        }

        if (response == null) {
            return Response
                    .builder()
                    .status(false)
                    ._message("Api does not have valid credentials!")
                    ._payload(null)
                    .build();
        }
        return Response
                .builder()
                .status(true)
                ._message("Api Code Link = " + response)
                ._payload(response)
                .build();
    }
    @PostMapping("/update")
    public Response<Object> update(@RequestBody Update_Request updateRequest) {
        Response response = _service.update(updateRequest);
        if (response == null) {
            return Response
                    .builder()
                    .status(false)
                    ._message("User not found")
                    ._payload(null)
                    .build();
        }
        return Response
                .builder()
                .status(true)
                ._message("User logged in")
                ._payload(response)
                .build();
    }

}
