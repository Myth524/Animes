package com.proyecto.animes.services;

import com.proyecto.animes.models.Info;
import com.proyecto.animes.repositories.IInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class InfoService implements IInfo {

    // Atributo

    @Autowired
    private IInfoDAO infoDAO;

    // Metodos

    @Override
    public ArrayList<Info> getAllAnimes() {
        return (ArrayList<Info>) infoDAO.findAll();
    }

    @Override
    public Optional<Info> getAnimeById(long id) {
        return infoDAO.findById(id);
    }

    @Override
    public Info saveAnime(Info anime) {
        return infoDAO.save(anime);
    }

    @Override
    public boolean deleteAnime(long id) {
        try {
            Optional<Info> anime = infoDAO.findById(id);
            infoDAO.delete(anime.get());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
