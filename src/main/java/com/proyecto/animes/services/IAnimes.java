package com.proyecto.animes.services;

import com.proyecto.animes.models.Animes;

import java.util.ArrayList;
import java.util.Optional;

public interface IAnimes {

    // Metodos Abstractos

    ArrayList<Animes> getAllAnimes();
    Optional<Animes> getAnimeById(long id);
    Animes saveAnime(Animes anime);
    boolean deleteAnime(long id);
}
