package com.proyecto.animes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Abilities")
public class Abilities {

    // Columnas

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(unique = true, nullable = false)
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column
        private String description;

    // Relaciones

        // Relacion Abilities - Characters (n a 1)
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "characters_id")
        @JsonIgnore
        private Characters characters;

    // Constructores

        public Abilities() {
        }

        public Abilities(String name, String description, Characters characters) {
            this.name = name;
            this.description = description;
            this.characters = characters;
        }

    // Getters and Setter

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Characters getCharacters() {
            return characters;
        }

        public void setCharacters(Characters characters) {
            this.characters = characters;
        }
}
