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
@Table(name="Studio")
public class Studios {

    // Columnas

        @Getter
        @Setter
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(unique = true, nullable = false)
        private Long id;

        @Getter
        @Setter
        @Column(unique = true)
        private String name;

        @Getter
        @Setter
        @Column
        private String country;

    // Relaciones

        // Relacion Studio - Animes (1 a 1)
        @OneToOne
        @JoinColumn(name = "anime_id")
        @JsonIgnore
        private Animes animes;

}
