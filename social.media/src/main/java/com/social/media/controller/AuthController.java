package com.social.media.controller;

import com.social.media.config.JwtProvider;
import com.social.media.model.User;
import com.social.media.repository.UserRepository;
import com.social.media.request.LoginRequest;
import com.social.media.response.AuthResponse;
import com.social.media.service.CustomUserDetailService;
import com.social.media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {

        User isExist =userRepository.findByEmail(user.getEmail());

        if(isExist!=null){
            throw new Exception("User already exists with this email");
        }
        User newUser=new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser =userRepository.save(newUser);

        UsernamePasswordAuthenticationToken authentication =new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());

        String token= JwtProvider.generateToken(authentication);

        AuthResponse response=new AuthResponse(token,"Resgister Success");
        return response;
    }

    @PostMapping("/signin")

    public AuthResponse signin(@RequestBody LoginRequest loginRequest){
        Authentication authentication=authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        String token= JwtProvider.generateToken(authentication);

        AuthResponse response=new AuthResponse(token,"Login Success");
        return response;

    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails=customUserDetailService.loadUserByUsername(email);
        if(userDetails==null){
            throw new BadCredentialsException("Invalid username");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
