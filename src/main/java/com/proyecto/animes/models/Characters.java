package com.proyecto.animes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Characters")
public class Characters {

    // Columnas

        @Getter
        @Setter
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(unique = true, nullable = false)
        private Long id;

        @Getter
        @Setter
        @Column
        private String name;

        @Getter
        @Setter
        @Column
        private String lastname;

        @Getter
        @Setter
        @Column
        private int age;

   // Relaciones

        // Relacion Characters - Animes (n a 1)
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "anime_id")
        @JsonIgnore
        private Animes animes;

        // Relacion Characters - Abilities (1 a n)
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "characters",cascade = CascadeType.ALL)
        private List<Abilities> abilities;

}