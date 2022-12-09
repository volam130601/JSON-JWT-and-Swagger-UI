package com.metabook.controller;

import com.metabook.config.jwt.TokenManager;
import com.metabook.dto.ResponseObject;
import com.metabook.dto.payload.LoginRequest;
import com.metabook.dto.payload.LoginResponse;
import com.metabook.entity.User;
import com.metabook.service.user.UserDetailsServiceImpl;
import com.metabook.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Tag(name = "login")
public class LoginController {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;

    @PostMapping(value = "/login" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> createToken(@RequestBody LoginRequest request) throws Exception {
        try {
            System.out.println(request);
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword())
            );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(jwtToken));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@Valid @RequestBody User user) {
        System.out.println(user);
        return ResponseEntity.ok(
                new ResponseObject(userService.register(user), "Register success", "SUCCESS", 1)
        );
    }

    @GetMapping("/remember-me")
    public ResponseEntity<ResponseObject> rememberMe() {
        System.out.println("Check remember-me");
        return ResponseEntity.ok(new ResponseObject());
    }

}
