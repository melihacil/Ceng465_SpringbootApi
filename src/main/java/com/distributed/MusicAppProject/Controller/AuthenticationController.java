package com.distributed.MusicAppProject.Controller;

//import com.distributed.MusicAppProject.DataObject.AuthenticationResponse;

import com.distributed.MusicAppProject.DataObject.Login_Request;
import com.distributed.MusicAppProject.DataObject.Register_Request;
import com.distributed.MusicAppProject.DataObject.Response;
import com.distributed.MusicAppProject.DataObject.Update_Request;
import com.distributed.MusicAppProject.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService _service;


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


    @PutMapping("/update")
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
