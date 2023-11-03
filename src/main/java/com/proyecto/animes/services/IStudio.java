package com.proyecto.animes.services;

import com.proyecto.animes.models.Studios;

import java.util.ArrayList;
import java.util.Optional;

public interface IStudio {

    // Metodos Abstractos

    ArrayList<Studios> getAllStudios();
    Optional<Studios> getStudioById(long id);
    Studios saveStudio(Studios studios);
    boolean deleteStudio(long id);
}
