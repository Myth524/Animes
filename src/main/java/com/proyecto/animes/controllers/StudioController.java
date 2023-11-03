package com.proyecto.animes.controllers;

import com.proyecto.animes.models.Studios;
import com.proyecto.animes.services.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/animes/studios")
public class StudioController {

    // Atributo

        @Autowired
        private StudioService studioService;

    // Peticiones

        // GET /studios (Traer todos los studios)
        @GetMapping("/findAll")
        public ResponseEntity<List<Studios>> getAllStudios() {
            List<Studios> studios = studioService.getAllStudios();
            return ResponseEntity.ok(studios);
        }

        // GET /studios/find/:id (Traer studio por id)
        @GetMapping("/find/{id}")
        public ResponseEntity<?> getStudioById(@PathVariable Long id) {
            Optional<Studios> studio = studioService.getStudioById(id);

            if (studio.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(studio.get());
        }

        // POST /studios/add (Agregar studios)


        // DELETE /studios/delete/:id (Eliminar studio por id)
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteStudio(@PathVariable long id) {
            if (studioService.deleteStudio(id)) {
                return ResponseEntity.ok("Se ha eliminado el studio correctamente");
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        // PUT /studios/update/:id (Actualizar studio por id)
        /***/
        @PutMapping("/update/{id}")
        public ResponseEntity<Studios> editStudio(@PathVariable long id, @RequestBody Studios studios) {

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
