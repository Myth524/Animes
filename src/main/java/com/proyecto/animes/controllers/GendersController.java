package com.proyecto.animes.controllers;

import com.proyecto.animes.models.Genders;
import com.proyecto.animes.services.GendersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/animes/genders")
public class GendersController {

    // Atributo

        @Autowired
        private GendersService gendersService;

    // Peticiones

        // GET /genders (Traer todos los generos)
        @GetMapping("/findAll")
        public ResponseEntity<List<Genders>> getAllGenders() {
            List<Genders> genders = gendersService.getAllGenders();
            return ResponseEntity.ok(genders);
        }

        // GET /genders/find/:id (Traer genero por id)
        @GetMapping("/find/{id}")
        public ResponseEntity<?> getGenderById(@PathVariable Long id) {
            Optional<Genders> gender = gendersService.getGenderById(id);

            if (gender.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(gender.get());
        }

        // DELETE /genders/delete/:id (Eliminar genero por id)
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteGender(@PathVariable long id) {
            if (gendersService.deleteGender(id)) {
                return ResponseEntity.ok("Se ha eliminado el genero correctamente");
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        // PUT /genders/update/:id (Actualizar genero por id)
        /***/
        @PutMapping("/update/{id}")
        public ResponseEntity<Genders> editGender(@PathVariable long id, @RequestBody Genders genders) {

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
