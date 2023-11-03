package com.proyecto.animes.services;

import com.proyecto.animes.models.Animes;
import com.proyecto.animes.repositories.IInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AnimesService implements IAnimes {

    // Atributo

    @Autowired
    private IInfoDAO infoDAO;

    // Metodos

    @Override
    public ArrayList<Animes> getAllAnimes() {
        return (ArrayList<Animes>) infoDAO.findAll();
    }

    @Override
    public Optional<Animes> getAnimeById(long id) {
        return infoDAO.findById(id);
    }

    @Override
    public Animes saveAnime(Animes anime) {
        return infoDAO.save(anime);
    }

    @Override
    public boolean deleteAnime(long id) {
        try {
            Optional<Animes> anime = infoDAO.findById(id);
            infoDAO.delete(anime.get());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
