package com.proyecto.animes.repositories;

import com.proyecto.animes.models.Characters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICharachtersDAO extends JpaRepository<Characters, Long> {

}
