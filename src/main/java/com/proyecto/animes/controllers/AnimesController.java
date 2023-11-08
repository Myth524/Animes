package com.proyecto.animes.controllers;

import com.proyecto.animes.models.*;
import com.proyecto.animes.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/animes")
public class AnimesController {

    // Atributo

        @Autowired
        private AnimesService animesService;

    // Peticiones

        // GET /animes (Traer todos los animes)
        @GetMapping("/findAll")
        public ModelAndView getAllAnimes() {
            List<Animes> animes = animesService.getAllAnimes();
            ModelAndView modelAndView = new ModelAndView("animesFindAll");
            modelAndView.addObject("animes", animes);
            return modelAndView;
        }

        // GET /animes/find/:id (Traer anime por id)
        @GetMapping("/find/{id}")
        public ResponseEntity<?> getAnimeById(@PathVariable Long id) {
            Optional<Animes> anime = animesService.getAnimeById(id);

            if (anime.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(anime.get());
        }

        // GET /animes/new (Formulario para agregar anime)
        @GetMapping("/new")
        public ModelAndView newAnime() {
            ModelAndView modelAndView = new ModelAndView("animesForm");
            modelAndView.addObject("animes", new Animes());
            return modelAndView;
        }

    // GET /animes/edit/{id} (Formulario para editar anime)
    @GetMapping("/edit/{id}")
    public ModelAndView editAnime(@PathVariable Long id) {
        Optional<Animes> anime = animesService.getAnimeById(id);

        ModelAndView modelAndView = new ModelAndView("editAnimesForm");
        modelAndView.addObject("anime", anime.get());
        return modelAndView;
    }

        // POST /animes/add (Agregar Anime)
        @PostMapping("/add")
        public ModelAndView saveAnime(@Valid Animes anime, BindingResult result) {
            if (result.hasErrors()) {
                ModelAndView modelAndView = new ModelAndView("animesForm");
                modelAndView.addObject("animes", anime);
                modelAndView.addObject("errors", result.getAllErrors());
                return modelAndView;
            } else {
                Studios studio = anime.getStudios();
                List<Characters> characters = anime.getCharacters();
                List<Genders> genders = anime.getGenders();
                animesService.insertAnimeData(anime, studio, characters, genders);
                return new ModelAndView("redirect:/api/animes/findAll");
            }
        }

        // DELETE /animes/delete/:id (Eliminar anime por id)
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteAnime(@PathVariable long id) {
            if (animesService.deleteAnime(id)) {
                return ResponseEntity.ok("Se ha eliminado el anime correctamente");
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        // PUT /animes/update/:id (Actualizar anime por id)
        @PutMapping("/update/{id}")
        public ResponseEntity<Animes> editAnime(@PathVariable long id, @RequestBody Animes animes) {
            Optional<Animes> anime = animesService.getAnimeById(id);

            if (anime.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Animes edit = anime.get();
            edit.setName(animes.getName());
            edit.setDescription(animes.getDescription());
            edit.setReleaseDate(animes.getReleaseDate());

            Animes updatedAnime = animesService.saveAnime(edit);
            return ResponseEntity.ok(updatedAnime);
        }

    // Metodos utilitarios

        // Método para construir el cuerpo de respuesta de validación de errores
        private Map<String, Object> buildValidationErrorBody(BindingResult result) {
            Map<String, Object> response = new HashMap<>();
            List<String> errors = new ArrayList<>();

            for (FieldError err : result.getFieldErrors()) {
                errors.add("El campo " + err.getField() + " " + err.getDefaultMessage());
            }

            response.put("Errors", errors);
            return response;
        }

        // Método para construir el cuerpo de respuesta de errores generales
        private Map<String, Object> buildErrorBody(String errorMessage) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", errorMessage);
            return response;
        }

}
