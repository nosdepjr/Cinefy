package dev.cinefy.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String username;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String password;
}
