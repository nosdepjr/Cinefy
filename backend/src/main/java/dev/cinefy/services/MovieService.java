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

        Movie created = movieRepository.save(movie);

        return MovieMapper.toMovieResponse(created);
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

    public List<MovieResponse> findMoviesByCategory(Long id){
        List<Movie> moviesFound = movieRepository.findByCategories_Id(id);

        return moviesFound.stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
    }

    private List<Category> findCategories(List<Category> categories){
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

    public MovieResponse updateMovie(Long id, MovieRequest request){
        Movie movie = movieRepository.findById(id)
                .orElse(null);

        if (movie == null){
            return null;
        }

        if (request.title() != null){
            movie.setTitle(request.title());
        }

        if (request.description() != null){
            movie.setDescription(request.description());
        }

        if (request.releaseDate() != null){
            movie.setReleaseDate(request.releaseDate());
        }

        if (request.rating() != null){
            movie.setRating(request.rating());
        }

        if (request.categoryIds() != null){
            List<Category> categories = request.categoryIds()
                    .stream()
                    .map(categoryService::findEntityById)
                    .filter(Objects::nonNull)
                    .toList();

            movie.setCategories(categories);
        }

        if (request.streamingIds() != null){
            List<Streaming> streamings = request.streamingIds()
                    .stream()
                    .map(streamingService::findEntityById)
                    .filter(Objects::nonNull)
                    .toList();

            movie.setStreamings(streamings);
        }

        Movie updated = movieRepository.save(movie);

        return MovieMapper.toMovieResponse(updated);
    }

    public MovieResponse deleteMovieById(Long id){
        Movie movie = movieRepository.findById(id)
                .orElse(null);
        movieRepository.deleteById(id);

        return movie != null? MovieMapper.toMovieResponse(movie):null;

    }
}
