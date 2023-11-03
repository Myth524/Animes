package com.proyecto.animes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Genders")
public class Genders {

    // Columnas

        @Getter
        @Setter
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(unique = true, nullable = false)
        private Long id;

        @Getter
        @Setter
        @Column(unique = true, nullable = false)
        private String name;

    // Relaciones

        // Relacion Genders - Animes (n a n)
        @ManyToMany()
        @JoinTable(
                name = "Animes_Genders", joinColumns = @JoinColumn(name = "genders_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "anime_id", referencedColumnName = "id")
        )
        @JsonIgnore
        private List<Animes> animes = new ArrayList<>();

}
