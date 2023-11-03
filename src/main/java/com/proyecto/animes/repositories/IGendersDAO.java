package com.proyecto.animes.repositories;

import com.proyecto.animes.models.Genders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGendersDAO extends JpaRepository<Genders, Long> {
}
