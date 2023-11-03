package com.proyecto.animes.services;

import com.proyecto.animes.models.Genders;

import java.util.ArrayList;
import java.util.Optional;

public interface IGenders {

    // Metodos Abstractos

    ArrayList<Genders> getAllGenders();
    Optional<Genders> getGenderById(long id);
    Genders saveGender(Genders gender);
    boolean deleteGender(long id);
}
