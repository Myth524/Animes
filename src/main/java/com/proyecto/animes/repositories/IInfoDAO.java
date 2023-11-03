package com.proyecto.animes.repositories;

import com.proyecto.animes.models.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInfoDAO extends JpaRepository<Info, Long> {
}
