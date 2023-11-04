package com.proyecto.animes.controllers;

import com.proyecto.animes.models.*;
import com.proyecto.animes.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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
        public ResponseEntity<List<Animes>> getAllAnimes() {
            List<Animes> animes = animesService.getAllAnimes();
            return ResponseEntity.ok(animes);
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

        // POST /animes/add (Agregar Anime)
        @PostMapping("/add")
        public ResponseEntity<?> saveAnime(@Valid @RequestBody Animes anime, BindingResult result) {
            if (result.hasErrors()) {
                return ResponseEntity.badRequest().body("Error en la solicitud.");
            }
            try {
                Studios studio = anime.getStudios();
                List<Characters> characters = anime.getCharacters();
                List<Genders> genders = anime.getGenders();

                animesService.insertAnimeData(anime, studio, characters, genders);
                return ResponseEntity.ok("Anime agregado con éxito.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al agregar el anime");
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
