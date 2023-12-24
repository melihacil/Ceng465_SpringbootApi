package com.distributed.MusicAppProject.Services;

import com.distributed.MusicAppProject.DataObject.Login_Request;
import com.distributed.MusicAppProject.DataObject.Register_Request;
import com.distributed.MusicAppProject.DataObject.Response;
import com.distributed.MusicAppProject.MusicUsers.AppUsers.AppUser;
import com.distributed.MusicAppProject.MusicUsers.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo _userRepo;

    public Response register(Register_Request registerRequest) {

        if (_userRepo.existsByUsername(registerRequest.getUsername())) {
            //throw exception in console
            System.out.println("User already exists");
            return null;
        }
        else {
            //create user
            var user = AppUser.builder()
                    .username(registerRequest.getUsername())
                    //.password(passwordEncoder.encode(registerRequest.getPassword()))
                    //.role(Role.USER)
                    .build();
            //save user
            _userRepo.save(user);
            //generate token
            //var jwtToken = jwtService.generateToken(user);
            //return token
            return Response
                    .builder()
                    .build();
        }
    }

    public Response login(Login_Request loginRequest) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword()
//                )
//        );
//        var user = userRepository.findByUsername(loginRequest.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        return jwtService.generateToken(user);
        return Response
                .builder()
                .build();
    }
}
