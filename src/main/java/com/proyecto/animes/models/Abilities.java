package com.proyecto.animes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Abilities")
public class Abilities {

    // Columnas

        @Getter
        @Setter
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(unique = true, nullable = false)
        private Long id;

        @Getter
        @Setter
        @Column(nullable = false)
        private String name;

        @Getter
        @Setter
        @Column
        private String description;

    // Relaciones

        // Relacion Abilities - Characters (n a 1)
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "characters_id")
        @JsonIgnore
        private Characters characters;

}
