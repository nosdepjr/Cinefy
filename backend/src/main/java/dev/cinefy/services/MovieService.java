package dev.cinefy.services;

import dev.cinefy.controllers.request.MovieRequest;
import dev.cinefy.controllers.response.MovieResponse;
import dev.cinefy.entities.Movie;
import dev.cinefy.mappers.MovieMapper;
import dev.cinefy.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService{
    private final MovieRepository movieRepository;

    public MovieResponse createMovie(MovieRequest movieRequest){
        return MovieMapper.toMovieResponse(movieRepository.save(MovieMapper.toMovie(movieRequest)));
    }

    public List<MovieResponse> findAllMovies(){
        List<Movie> movies = movieRepository.findAll();
        return movies
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
    }

    public MovieResponse findMovieById(Long id){
        Movie movie = movieRepository
                .findById(id)
                .orElse(null);
        return movie != null? MovieMapper.toMovieResponse(movie):null;
    }

    public void deleteMovieById(Long id){
        movieRepository.deleteById(id);
    }
}
