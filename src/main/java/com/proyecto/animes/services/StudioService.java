package com.proyecto.animes.services;

import com.proyecto.animes.models.Studio;
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
    public ArrayList<Studio> getAllStudios() {
        return (ArrayList<Studio>) studioDAO.findAll();
    }

    @Override
    public Optional<Studio> getStudioById(long id) {
        return studioDAO.findById(id);
    }

    @Override
    public Studio saveStudio(Studio studio) {
        return studioDAO.save(studio);
    }

    @Override
    public boolean deleteStudio(long id) {
        try {
            Optional<Studio> studio = studioDAO.findById(id);
            studioDAO.delete(studio.get());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
