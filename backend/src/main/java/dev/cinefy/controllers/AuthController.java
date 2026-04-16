package dev.cinefy.controllers;

import dev.cinefy.controllers.request.LoginRequest;
import dev.cinefy.controllers.request.UserRequest;
import dev.cinefy.controllers.response.LoginResponse;
import dev.cinefy.controllers.response.UserResponse;
import dev.cinefy.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @RequestMapping(value = "/validate", method = RequestMethod.HEAD)
    public ResponseEntity<Void> validateToken() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }
}
