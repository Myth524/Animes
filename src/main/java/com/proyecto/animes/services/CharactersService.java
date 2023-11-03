package com.proyecto.animes.services;

import com.proyecto.animes.models.Characters;
import com.proyecto.animes.repositories.ICharachtersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CharactersService implements ICharacters {

    // Atributo

    @Autowired
    private ICharachtersDAO charachtersDAO;

    // Metodos

    @Override
    public ArrayList<Characters> getAllCharacters() {
        return (ArrayList<Characters>) charachtersDAO.findAll();
    }

    @Override
    public Optional<Characters> getCharacterById(long id) {
        return charachtersDAO.findById(id);
    }

    @Override
    public Characters saveCharacter(Characters character) {
        return charachtersDAO.save(character);
    }

    @Override
    public boolean deleteCharacter(long id) {
        try {
            Optional<Characters>  character = charachtersDAO.findById(id);
            charachtersDAO.delete(character.get());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
