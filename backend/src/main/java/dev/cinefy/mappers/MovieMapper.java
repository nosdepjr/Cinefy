package dev.cinefy.mappers;

import dev.cinefy.controllers.request.MovieRequest;
import dev.cinefy.controllers.response.CategoryResponse;
import dev.cinefy.controllers.response.MovieResponse;
import dev.cinefy.controllers.response.StreamingResponse;
import dev.cinefy.entities.Category;
import dev.cinefy.entities.Movie;
import dev.cinefy.entities.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper{
    public static Movie toMovie(MovieRequest request){
        List<Category> categoriesFound = request
                .categoryIds()
                .stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();
        List<Streaming> streamingsFound = request
                .streamingIds()
                .stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        return Movie
                .builder()
                .title(request.title())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .rating(request.rating())
                .categories(categoriesFound)
                .streamings(streamingsFound)
                .build();

    }

    public static MovieResponse toMovieResponse(Movie movie){
        List<CategoryResponse> categories = movie
                .getCategories()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
        List<StreamingResponse> streamings = movie
                .getStreamings()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

        return MovieResponse.builder()
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }
}
