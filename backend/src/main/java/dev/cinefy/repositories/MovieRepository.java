package dev.cinefy.repositories;

import dev.cinefy.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
    List<Movie> findByCategories_Id(Long categoryId);
}
