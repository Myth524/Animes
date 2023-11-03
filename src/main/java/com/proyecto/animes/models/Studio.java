package com.proyecto.animes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="Studio")
public class Studio {

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

        // Relacion Studio - Info (1 a 1)
        @OneToOne
        @JoinColumn(name = "id_anime")
        @JsonIgnore
        private Info info;

    // Constructores

    public Studio() {
    }

    public Studio(String name, String country, Info info) {
        this.name = name;
        this.country = country;
        this.info = info;
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

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
