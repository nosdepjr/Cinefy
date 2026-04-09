package dev.cinefy.controllers.request;

public record UserRequest(String username,
                          String email,
                          String password){
}
