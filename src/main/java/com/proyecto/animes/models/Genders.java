package com.proyecto.animes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Genders")
public class Genders {

    // Columnas

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    // Relaciones

        // Relacion Genders - Info (n a n)
        @ManyToMany()
        @JoinTable(
                name = "Info_Genders", joinColumns = @JoinColumn(name = "genders_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "anime_id", referencedColumnName = "id")
        )
        @JsonIgnore
        private List<Info> info = new ArrayList<>();

    // Constructores

        public Genders() {
        }

        public Genders(String name) {
            this.name = name;
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

        public List<Info> getInfo() {
            return info;
        }

        public void setInfo(List<Info> info) {
            this.info = info;
        }
}
