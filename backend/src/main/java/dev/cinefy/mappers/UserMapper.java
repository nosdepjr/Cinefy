package dev.cinefy.mappers;

import dev.cinefy.controllers.request.UserRequest;
import dev.cinefy.controllers.response.UserResponse;
import dev.cinefy.entities.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper{
    public static User toUser(UserRequest request){
        return User.builder()
                .username(request.username())
                .email(request.email())
                .password(request.password())
                .build();
    }

    public static UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
