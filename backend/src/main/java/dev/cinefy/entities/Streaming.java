package dev.cinefy.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_streaming")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Streaming{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;
}
