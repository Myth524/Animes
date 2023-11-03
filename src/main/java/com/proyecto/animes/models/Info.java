package com.proyecto.animes.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name="Info")
public class Info {

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

        // Relacion Info - Studio (1 a 1)
        @OneToOne(mappedBy = "info")
        private Studio studio;

        // Relacion Info - Character (1 a n)
        @OneToMany(mappedBy = "info")
        private List<Characters> characters;

        // Relacion Info - Genders (n a n)
        @ManyToMany(mappedBy = "info")
        private List<Genders> genders;


    // Constructores

        public Info() {
        }

        public Info(String name, String description, Date releaseDate, int episodes, Studio studio, List<Characters> characters, List<Genders> genders) {
            this.name = name;
            this.description = description;
            this.releaseDate = releaseDate;
            this.episodes = episodes;
            this.studio = studio;
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

        public Studio getStudio() {
            return studio;
        }

        public void setStudio(Studio studio) {
            this.studio = studio;
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
