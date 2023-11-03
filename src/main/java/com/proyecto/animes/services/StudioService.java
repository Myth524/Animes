package com.proyecto.animes.services;

import com.proyecto.animes.models.Studios;
import com.proyecto.animes.repositories.IStudioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StudioService implements IStudio {

    // Atributo

    @Autowired
    private IStudioDAO studioDAO;

    // Metodos

    @Override
    public ArrayList<Studios> getAllStudios() {
        return (ArrayList<Studios>) studioDAO.findAll();
    }

    @Override
    public Optional<Studios> getStudioById(long id) {
        return studioDAO.findById(id);
    }

    @Override
    public Studios saveStudio(Studios studios) {
        return studioDAO.save(studios);
    }

    @Override
    public boolean deleteStudio(long id) {
        try {
            Optional<Studios> studio = studioDAO.findById(id);
            studioDAO.delete(studio.get());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
