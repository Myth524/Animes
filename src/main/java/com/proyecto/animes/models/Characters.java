package com.proyecto.animes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Characters")
public class Characters {

    // Columnas

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String lastname;

    @Column
    private int age;

   // Relaciones

    // Relacion Characters - Info (n a 1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anime_id")
    @JsonIgnore
    private Info info;

    // Relacion Characters - Abilities (1 a n)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "characters",cascade = CascadeType.ALL)
    private List<Abilities> abilities;

    // Constructores

    public Characters() {
    }

    public Characters(String name, String lastname, int age) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
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

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Abilities> abilities) {
        this.abilities = abilities;
    }
}
