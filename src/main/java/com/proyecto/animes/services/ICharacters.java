package com.proyecto.animes.services;

import com.proyecto.animes.models.Characters;

import java.util.ArrayList;
import java.util.Optional;

public interface ICharacters {

    // Metodos Abstractos

    ArrayList<Characters> getAllCharacters();
    Optional<Characters> getCharacterById(long id);
    Characters saveCharacter(Characters character);
    boolean deleteCharacter(long id);
}
