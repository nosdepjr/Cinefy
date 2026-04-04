package dev.cinefy.services;

import dev.cinefy.controllers.request.MovieRequest;
import dev.cinefy.controllers.response.MovieResponse;
import dev.cinefy.mappers.MovieMapper;
import dev.cinefy.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService{
    private final MovieRepository movieRepository;

    public MovieResponse createMovie(MovieRequest movieRequest){
        return MovieMapper.toMovieResponse(movieRepository.save(MovieMapper.toMovie(movieRequest)));
    }
}
