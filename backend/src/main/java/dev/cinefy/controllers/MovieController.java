package dev.cinefy.controllers;

import dev.cinefy.controllers.request.MovieRequest;
import dev.cinefy.controllers.response.MovieResponse;
import dev.cinefy.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController{
    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse> createMovie(@RequestBody MovieRequest request){
        MovieResponse response = movieService.createMovie(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAllMovies(){
        return ResponseEntity.ok(movieService.findAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findMovieById(@PathVariable Long id){
        MovieResponse response = movieService.findMovieById(id);

        if (response == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findMoviesByCategory(@RequestParam(required = false) Long categoryId){
        if (categoryId != null){
            return ResponseEntity.ok(movieService.findMoviesByCategory(categoryId));
        }

        return ResponseEntity.ok(movieService.findAllMovies());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(
            @PathVariable Long id,
            @RequestBody MovieRequest request
    ){
        MovieResponse response = movieService.updateMovie(id, request);

        if (response == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        movieService.deleteMovieById(id);
        return ResponseEntity.noContent().build();
    }
}
