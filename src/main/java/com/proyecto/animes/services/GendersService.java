package com.proyecto.animes.services;

import com.proyecto.animes.models.Genders;
import com.proyecto.animes.repositories.IGendersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class GendersService implements IGenders {

    // Atributo

    @Autowired
    private IGendersDAO gendersDAO;

    // Metodos

    @Override
    public ArrayList<Genders> getAllGenders() {
        return (ArrayList<Genders>) gendersDAO.findAll();
    }

    @Override
    public Optional<Genders> getGenderById(long id) {
        return gendersDAO.findById(id);
    }

    @Override
    public Genders saveGender(Genders gender) {
        return gendersDAO.save(gender);
    }

    @Override
    public boolean deleteGender(long id) {
        try {
            Optional<Genders> gender = gendersDAO.findById(id);
            gendersDAO.delete(gender.get());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
