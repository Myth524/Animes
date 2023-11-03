package com.proyecto.animes.repositories;

import com.proyecto.animes.models.Studios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudioDAO extends JpaRepository<Studios, Long> {
}
