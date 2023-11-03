package com.proyecto.animes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Animes")
public class Animes {

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

        @Getter
        @Setter
        @Column
        private String description;

        @Getter
        @Setter
        @Column
        private Date releaseDate;

        @Getter
        @Setter
        @Column
        private int episodes;

    // Relaciones

        // Relacion Animes - Studio (1 a 1)
        @OneToOne(mappedBy = "animes")
        private Studios studios;

        // Relacion Animes - Character (1 a n)
        @OneToMany(mappedBy = "animes")
        private List<Characters> characters;

        // Relacion Animes - Genders (n a n)
        @ManyToMany(mappedBy = "animes")
        private List<Genders> genders;

}
