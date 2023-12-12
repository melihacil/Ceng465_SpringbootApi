package com.distributed.MusicAppProject.Services;

import com.distributed.MusicAppProject.DataObject.AuthenticationResponse;
import com.distributed.MusicAppProject.DataObject.Login_Request;
import com.distributed.MusicAppProject.DataObject.Register_Request;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    public AuthenticationResponse register(Register_Request registerRequest) {
//        //check if user exists
//        if (userRepository.existsByUsername(registerRequest.getUsername())) {
//            //throw exception in console
//            System.out.println("User already exists");
//            return null;
//        }
//        else {
//            //create user
//            var user = User.builder()
//                    .username(registerRequest.getUsername())
//                    .password(passwordEncoder.encode(registerRequest.getPassword()))
//                    .role(Role.USER)
//                    .build();
//            //save user
//            userRepository.save(user);
//            //generate token
//            var jwtToken = jwtService.generateToken(user);
//            //return token
//            return AuthenticationResponse
//                    .builder()
//                    .accessToken(jwtToken)
//                    .build();
//        }

        return new AuthenticationResponse()
                .builder()
                //.accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse login(Login_Request loginRequest) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword()
//                )
//        );
//        var user = userRepository.findByUsername(loginRequest.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse
//                .builder()
//                .accessToken(jwtToken)
//                .build();
//    }
        return new AuthenticationResponse()
                .builder()
               // .accessToken(jwtToken)
                .build();
    }
}
