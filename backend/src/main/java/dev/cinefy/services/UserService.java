package dev.cinefy.services;

import dev.cinefy.controllers.request.UserRequest;
import dev.cinefy.controllers.response.UserResponse;
import dev.cinefy.entities.User;
import dev.cinefy.mappers.UserMapper;
import dev.cinefy.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;

    public UserResponse userResponseTest(UserRequest request){
        User user = UserMapper.toUser(request);
        System.out.println(user.getId() + ", " + user.getUsername() +  ", " + user.getEmail() + ", " + user.getPassword());
        return UserMapper.toUserResponse(user);
    }

}
