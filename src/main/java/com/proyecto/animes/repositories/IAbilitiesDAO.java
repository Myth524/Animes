package com.proyecto.animes.repositories;

import com.proyecto.animes.models.Abilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAbilitiesDAO extends JpaRepository<Abilities, Long> {
}
