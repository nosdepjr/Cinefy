package dev.cinefy.controllers;

import dev.cinefy.controllers.request.UserRequest;
import dev.cinefy.controllers.response.UserResponse;
import dev.cinefy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController{
    private final UserService userService;

    @GetMapping("/health")
    public ResponseEntity<UserResponse> healthChecker(){

        return ResponseEntity.ok(userService.userResponseTest(new UserRequest(
                "user1",
                "user1@email.com",
                "123")));
    }
}
