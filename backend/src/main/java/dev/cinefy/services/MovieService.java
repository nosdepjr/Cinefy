package dev.cinefy.services;

import dev.cinefy.controllers.request.MovieRequest;
import dev.cinefy.controllers.response.MovieResponse;
import dev.cinefy.entities.Category;
import dev.cinefy.entities.Movie;
import dev.cinefy.entities.Streaming;
import dev.cinefy.mappers.MovieMapper;
import dev.cinefy.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MovieService{
    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public MovieResponse createMovie(MovieRequest request){
        Movie movie = MovieMapper.toMovie(request);

        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));

        return MovieMapper.toMovieResponse(movieRepository.save(movie));
    }

    public List<MovieResponse> findAllMovies(){
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
    }

    public MovieResponse findMovieById(Long id){
        Movie movie = movieRepository.findById(id)
                .orElse(null);
        return movie != null? MovieMapper.toMovieResponse(movie):null;
    }

    private List<Category> findCategories(List<Category> categories) {
        return categories.stream()
                .map(category -> categoryService.findEntityById(category.getId()))
                .filter(Objects::nonNull)
                .toList();
    }

    private List<Streaming> findStreamings(List<Streaming> streamings){
        return streamings.stream()
                .map(category -> streamingService.findEntityById(category.getId()))
                .filter(Objects::nonNull)
                .toList();
    }

    public void deleteMovieById(Long id){
        movieRepository.deleteById(id);
    }
}
