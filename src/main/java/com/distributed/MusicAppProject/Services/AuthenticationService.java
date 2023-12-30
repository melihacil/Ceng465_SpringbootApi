package com.distributed.MusicAppProject.Services;

import com.distributed.MusicAppProject.DataObject.Requests.Login_Request;
import com.distributed.MusicAppProject.DataObject.Requests.Register_Request;
import com.distributed.MusicAppProject.DataObject.Response;
import com.distributed.MusicAppProject.DataObject.Requests.Update_Request;
import com.distributed.MusicAppProject.MusicUsers.AppUsers.AppUser;
import com.distributed.MusicAppProject.MusicUsers.AppUsers.Role;
import com.distributed.MusicAppProject.MusicUsers.UserRepo;
import com.distributed.MusicAppProject.Services.Interfaces.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo _userRepo;

    private final PasswordEncoder passEncoder;

    private final JWTService jwtService;

    private final AuthenticationManager authManager;
    public Response register(Register_Request registerRequest) {
        System.out.println("Register Method Starting");
        if (_userRepo.existsByUsername(registerRequest.getUsername())) {
            System.out.println("Checking");
            //throw exception in console
            System.out.println("User already exists");
            return null;
        }
        else {
            //create user
            System.out.println("Creating user");
            var user = AppUser.builder()
                    .username(registerRequest.getUsername())
                    .password(passEncoder.encode(registerRequest.getPassword()))
                    .role(Role.USER)
                    .build();
            //save user
            System.out.println("User created");
            _userRepo.save(user);
            System.out.println("Getting Jwt");
            var jwtToken = jwtService.generateToken(user);
            //generate token
            //var jwtToken = jwtService.generateToken(user);
            //return token
            System.out.println("Returning");
            return Response
                    .builder()
                    .build();
        }
    }


    // Şifre bakma eklenecek
    public String login(Login_Request loginRequest) {
        System.out.println("Login Request");
//        authManager.authenticate(new UsernamePasswordAuthenticationToken(
//                       loginRequest.getUsername(),
//                        loginRequest.getPassword()
//              )
//       );
        System.out.println("Login Request -- Authmanager finished");
        var user =  _userRepo.findByUsername(loginRequest.getUsername())
               .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("Login Request -- Returning");
        return jwtService.generateToken(user);
    }

    public Response update(Update_Request updateRequest) {
        System.out.println("Update Method Starting");
        if (_userRepo.existsByUsername(updateRequest.getUsername())) {
            AppUser user = _userRepo.findByUsername(updateRequest.getUsername()).get();
            System.out.println("Username   = " + user.getUsername() + " www" + updateRequest.getNewUserName());
            user.setUsername(updateRequest.getNewUserName());
            System.out.println("Username   = " + user.getUsername());
            //save user
            System.out.println("User updated");
            _userRepo.save(user);
            System.out.println("Getting Jwt");
            var jwtToken = jwtService.generateToken(user);
            //generate token
            //var jwtToken = jwtService.generateToken(user);
            //return token
            System.out.println("Returning");
            return Response
                    .builder()
                    .build();

        }
        else {
            System.out.println("User Does not exist");
            return null;

        }
    }

}
