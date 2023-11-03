package com.proyecto.animes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="Studio")
public class Studios {

    // Columnas

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private String country;

    // Relaciones

        // Relacion Studio - Animes (1 a 1)
        @OneToOne
        @JoinColumn(name = "anime_id")
        @JsonIgnore
        private Animes animes;

    // Constructores

    public Studios() {
    }

    public Studios(String name, String country, Animes animes) {
        this.name = name;
        this.country = country;
        this.animes = animes;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Animes getInfo() {
        return animes;
    }

    public void setInfo(Animes animes) {
        this.animes = animes;
    }
}
