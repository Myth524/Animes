package com.proyecto.animes.controllers;

import com.proyecto.animes.models.Characters;
import com.proyecto.animes.services.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/animes/characters")
public class CharactersController {

    // Atributo

        @Autowired
        private CharactersService charactersService;

    // Peticiones

        // GET /characters (Traer todos los personajes)
        @GetMapping("/findAll")
        public ResponseEntity<List<Characters>> getAllCharacters() {
            List<Characters> characters = charactersService.getAllCharacters();
            return ResponseEntity.ok(characters);
        }

        // GET /characters/find/:id (Traer personaje por id)
        @GetMapping("/find/{id}")
        public ResponseEntity<?> getCharacterById(@PathVariable Long id) {
            Optional<Characters> character = charactersService.getCharacterById(id);

            if (character.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(character.get());
        }

        // POST /characters/anime_id/add (Agregar personajes a un anime)

        // DELETE /characters/delete/:id (Eliminar personaje por id)
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteCharacter(@PathVariable long id) {
            if (charactersService.deleteCharacter(id)) {
                return ResponseEntity.ok("Se ha eliminado el personaje correctamente");
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        // PUT /characters/update/:id (Actualizar personaje por id)
    /***/
        @PutMapping("/update/{id}")
        public ResponseEntity<Characters> editCharacter(@PathVariable long id, @RequestBody Characters characters) {

            return ResponseEntity.ok(null);
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
