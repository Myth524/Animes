package com.proyecto.animes.repositories;

import com.proyecto.animes.models.Animes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInfoDAO extends JpaRepository<Animes, Long> {
}
