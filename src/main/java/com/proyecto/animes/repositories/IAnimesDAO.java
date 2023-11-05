package com.proyecto.animes.repositories;

import com.proyecto.animes.models.Animes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface IAnimesDAO extends JpaRepository<Animes, Long> {


    @Query(value = "INSERT INTO Animes (name, description, releaseDate, episodes) " +
            "VALUES (:name, :description, :releaseDate, :episodes)", nativeQuery = true)
    void insertAnimeData(String name, String description, Date releaseDate, int episodes);

    @Query(value = "INSERT INTO Studios (name, country, anime_id) " +
            "VALUES (:studioName, :studioCountry, (SELECT id FROM Animes WHERE name = :animeName))", nativeQuery = true)
    void insertStudioData(String studioName, String studioCountry, String animeName);

    @Query(value = "INSERT INTO Characters (name, lastname, age, anime_id) " +
            "VALUES (:characterName, :characterLastname, :characterAge, (SELECT id FROM Animes WHERE name = :animeName))", nativeQuery = true)
    void insertCharacterData(String characterName, String characterLastname, int characterAge, String animeName);

    @Query(value = "INSERT INTO Animes_Genders (anime_id, genders_id) " +
            "VALUES ((SELECT id FROM Animes WHERE name = :animeName), (SELECT id FROM Genders WHERE name = :genderName))", nativeQuery = true)
    void insertAnimeGenderRelation(String animeName, String genderName);

}
