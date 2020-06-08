package com.sturgeon.photobook.controller;

import com.sturgeon.photobook.bo.Role;
import com.sturgeon.photobook.bo.User;
import com.sturgeon.photobook.bo.UserDetailsImpl;
import com.sturgeon.photobook.dto.JwtResponse;
import com.sturgeon.photobook.dto.LoginRequest;
import com.sturgeon.photobook.dto.MessageResponse;
import com.sturgeon.photobook.dto.SignupRequest;
import com.sturgeon.photobook.repository.RoleRepository;
import com.sturgeon.photobook.repository.UserRepository;
import com.sturgeon.photobook.security.JwtUtils;
import com.sturgeon.photobook.service.RoleService;
import com.sturgeon.photobook.service.UserService;
import com.sturgeon.photobook.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signUpRequest) {
        if (userService.userExistsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.userExistByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

        List<Role> roles = Arrays.asList(roleService.getRole(Role.auth_role.USER));
        user.setRoles(roles);
        userService.saveUser(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
