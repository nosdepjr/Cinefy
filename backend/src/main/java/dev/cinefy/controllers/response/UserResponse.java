package dev.cinefy.controllers.response;

import lombok.Builder;

@Builder
public record UserResponse(String username,
                           String email){
}
