package com.proyecto.animes.services;

import com.proyecto.animes.models.Studio ;

import java.util.ArrayList;
import java.util.Optional;

public interface IStudio {

    // Metodos Abstractos

    ArrayList<Studio > getAllStudios();
    Optional<Studio > getStudioById(long id);
    Studio  saveStudio(Studio studio);
    boolean deleteStudio(long id);
}
