package com.proyecto.animes.services;

import com.proyecto.animes.models.Animes;
import com.proyecto.animes.models.Characters;
import com.proyecto.animes.models.Genders;
import com.proyecto.animes.models.Studios;
import com.proyecto.animes.repositories.IAnimesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimesService implements IAnimes {

    // Atributo

    @Autowired
    private IAnimesDAO animesDAO;

    // Metodos

    @Override
    public ArrayList<Animes> getAllAnimes() {
        return (ArrayList<Animes>) animesDAO.findAll();
    }

    @Override
    public Optional<Animes> getAnimeById(long id) {
        return animesDAO.findById(id);
    }

    @Override
    public Animes saveAnime(Animes anime) {
        return animesDAO.save(anime);
    }

    @Override
    public boolean deleteAnime(long id) {
        try {
            Optional<Animes> anime = animesDAO.findById(id);
            animesDAO.delete(anime.get());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void insertAnimeData(Animes anime, Studios studio, List<Characters> characters, List<String> genders) {
        // Insertar datos del anime
        Animes savedAnime = animesDAO.save(anime);

        // Asignar el anime al estudio
        studio.setAnimes(savedAnime);
        savedAnime.setStudios(studio);

        // Asignar los personajes al anime
        for (Characters character : characters) {
            character.setAnimes(savedAnime);
        }
    }
}
