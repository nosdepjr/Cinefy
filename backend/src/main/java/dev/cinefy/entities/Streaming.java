package dev.cinefy.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "streaming")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Streaming{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 100, nullable = false)
    String name;
}
