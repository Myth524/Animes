package com.proyecto.animes.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name="Info")
public class Animes {

    // Columnas
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(unique = true, nullable = false)
        private Long id;

        @Column(unique = true, nullable = false)
        private String name;

        @Column
        private String description;

        @Column
        private Date releaseDate;

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


    // Constructores

        public Animes() {
        }

        public Animes(String name, String description, Date releaseDate, int episodes, Studios studios, List<Characters> characters, List<Genders> genders) {
            this.name = name;
            this.description = description;
            this.releaseDate = releaseDate;
            this.episodes = episodes;
            this.studios = studios;
            this.characters = characters;
            this.genders = genders;
        }

    // Getters and Setters

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

        public Date getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(Date releaseDate) {
            this.releaseDate = releaseDate;
        }

        public int getEpisodes() {
            return episodes;
        }

        public void setEpisodes(int episodes) {
            this.episodes = episodes;
        }

        public Studios getStudio() {
            return studios;
        }

        public void setStudio(Studios studios) {
            this.studios = studios;
        }

        public List<Characters> getCharacters() {
            return characters;
        }

        public void setCharacters(List<Characters> characters) {
            this.characters = characters;
        }

        public List<Genders> getGenders() {
            return genders;
        }

        public void setGenders(List<Genders> genders) {
            this.genders = genders;
        }
}
