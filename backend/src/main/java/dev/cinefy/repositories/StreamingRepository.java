package dev.cinefy.repositories;

import dev.cinefy.entities.Streaming;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamingRepository extends JpaRepository<Streaming, Long>{
}
