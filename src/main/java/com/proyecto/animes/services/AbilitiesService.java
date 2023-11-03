package com.proyecto.animes.services;

import com.proyecto.animes.repositories.IAbilitiesDAO;
import com.proyecto.animes.models.Abilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AbilitiesService implements IAbilities {

    // Atributo

    @Autowired
    private IAbilitiesDAO abilitiesDAO;

    // Metodos

    @Override
    public ArrayList<Abilities> getAllAbilities() {
        return (ArrayList<Abilities>) abilitiesDAO.findAll();
    }

    @Override
    public Optional<Abilities> getAbilityById(long id) {
        return abilitiesDAO.findById(id);
    }

    @Override
    public Abilities saveAbility(Abilities ability) {
        return abilitiesDAO.save(ability);
    }

    @Override
    public boolean deleteAbility(long id) {
        try {
            Optional<Abilities> ability = getAbilityById(id);
            abilitiesDAO.delete(ability.get());
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
