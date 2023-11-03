package com.proyecto.animes.controllers;

import com.proyecto.animes.models.Abilities;
import com.proyecto.animes.services.AbilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/animes/abilities")
public class AbilitiesController {

    // Atributo

        @Autowired
        private AbilitiesService abilitiesService;

    // Peticiones

        // GET /abilities (Traer todos los studios)
        @GetMapping("/findAll")
        public ResponseEntity<List<Abilities>> getAllAbilities() {
            List<Abilities> abilities = abilitiesService.getAllAbilities();
            return ResponseEntity.ok(abilities);
        }

        // GET /abilities/find/:id (Traer habilidad por id)
        @GetMapping("/find/{id}")
        public ResponseEntity<?> getAbilityById(@PathVariable Long id) {
            Optional<Abilities> ability = abilitiesService.getAbilityById(id);

            if (ability.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(ability.get());
        }

        // POST /characters/character_id/add (Agregar habilidades a un personaje)

    /***/
        // DELETE /abilities/delete/:id (Eliminar habilidad por id)
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteStudio(@PathVariable long id) {
            if (abilitiesService.deleteAbility(id)) {
                return ResponseEntity.ok("Se ha eliminado el habilidad correctamente");
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        // PUT /abilities/update/:id (Actualizar habilidad por id)
        /***/
        @PutMapping("/update/{id}")
        public ResponseEntity<Abilities> editAbility(@PathVariable long id, @RequestBody Abilities abilities) {

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
