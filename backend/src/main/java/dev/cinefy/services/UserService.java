package dev.cinefy.services;

import dev.cinefy.controllers.request.LoginRequest;
import dev.cinefy.controllers.request.UserRequest;
import dev.cinefy.controllers.response.LoginResponse;
import dev.cinefy.controllers.response.UserResponse;
import dev.cinefy.entities.User;
import dev.cinefy.exceptions.UsernameOrPasswordInvalidException;
import dev.cinefy.mappers.UserMapper;
import dev.cinefy.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public UserResponse createUser(UserRequest request) {
        User user = UserMapper.toUser(request);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));

        return UserMapper.toUserResponse(userRepository.save(user));
    }

    public LoginResponse login(LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
            Authentication authenticate = authenticationManager.authenticate(userAndPass);

            User user = (User) authenticate.getPrincipal();

            String token = tokenService.generateToken(user);

            return new LoginResponse(token);
        } catch (BadCredentialsException e) {
            throw new UsernameOrPasswordInvalidException("Usuário ou senha inválidos.");
        }
    }
}
