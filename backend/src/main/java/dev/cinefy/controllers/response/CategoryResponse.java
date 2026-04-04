package dev.cinefy.controllers.response;

import lombok.Builder;

@Builder
public record CategoryResponse(Long id,
                               String name){
}
