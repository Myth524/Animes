package com.proyecto.animes.services;

import com.proyecto.animes.models.Abilities;

import java.util.ArrayList;
import java.util.Optional;

public interface IAbilities {

    // Metodos Abstractos

    ArrayList<Abilities> getAllAbilities();
    Optional<Abilities> getAbilityById(long id);
    Abilities saveAbility(Abilities ability);
    boolean deleteAbility(long id);

}
