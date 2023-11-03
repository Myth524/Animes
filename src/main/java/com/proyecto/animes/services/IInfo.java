package com.proyecto.animes.services;

import com.proyecto.animes.models.Info;

import java.util.ArrayList;
import java.util.Optional;

public interface IInfo {

    // Metodos Abstractos

    ArrayList<Info> getAllAnimes();
    Optional<Info> getAnimeById(long id);
    Info saveAnime(Info anime);
    boolean deleteAnime(long id);
}
