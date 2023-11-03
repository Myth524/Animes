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
public class ApiController {

    // Atributo

    @Autowired
    private InfoService infoService;

    @Autowired
    private StudioService studioService;

    @Autowired
    private CharactersService charactersService;

    @Autowired
    private AbilitiesService abilitiesService;

    @Autowired
    private GendersService gendersService;

    // Peticiones

        // GET /animes (Traer todos los animes)
        @GetMapping("/findAll")
        public ResponseEntity<List<Info>> getAllAnimes() {
            List<Info> allAnimes = infoService.getAllAnimes();
            return ResponseEntity.ok(allAnimes);
        }

        // GET /animes/find/:id (Traer anime por id)
        @GetMapping("/find/{id}")
        public ResponseEntity<?> getAnimeById(@PathVariable Long id) {
            Optional<Info> anime = infoService.getAnimeById(id);

            if (anime.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(anime.get());
        }

        // POST /animes/add (Crear anime)
        @PostMapping("/add")
        public ResponseEntity<?> saveAnime(@Valid @RequestBody Info anime, BindingResult result) {
            if (result.hasErrors()) {
                return ResponseEntity.badRequest().body(buildValidationErrorBody(result));
            }
            try {

                // Guardar estudio
                Studio studio = anime.getStudio();
                Studio savedStudio = studioService.saveStudio(studio);
                anime.setStudio(savedStudio);
                savedStudio.setInfo(anime);

                Info savedAnime = infoService.saveAnime(anime);

                // Guardar personajes
                List<Characters> characters = savedAnime.getCharacters();
                List<Characters> savedCharacters = new ArrayList<>();
                for (Characters character : characters) {
                    List<Abilities> abilities = character.getAbilities();

                    character.setInfo(savedAnime);
                    Characters savedCharacter = charactersService.saveCharacter(character);

                    // Guardar habilidades para el personaje
                    List<Abilities> savedAbilities = new ArrayList<>();
                    for (Abilities ability : abilities) {
                        ability.setCharacters(savedCharacter);
                        Abilities savedAbility = abilitiesService.saveAbility(ability);
                        savedAbilities.add(savedAbility);
                    }
                    savedCharacter.setAbilities(savedAbilities);

                    savedCharacters.add(savedCharacter);
                }
                savedAnime.setCharacters(savedCharacters);

                // Guardar géneros
                List<Genders> genders = savedAnime.getGenders();
                List<Genders> savedGenders = new ArrayList<>();
                for (Genders gender : genders) {
                    // Agregar el género a la lista de géneros en el anime
                    savedAnime.getGenders().add(gender);

                    // Agregar el anime a la lista de animes en el género
                    gender.getInfo().add(savedAnime);

                    Genders savedGender = gendersService.saveGender(gender);
                    savedGenders.add(savedGender);
                }
                savedAnime.setGenders(savedGenders);

                return ResponseEntity.ok(savedAnime);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(buildErrorBody(e.getMessage()));
            }
        }

        // DELETE /animes/delete/:id (Eliminar anime por id)
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteAnime(@PathVariable long id) {
            if (infoService.deleteAnime(id)) {
                return ResponseEntity.ok("Se ha eliminado el anime correctamente");
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        // PUT /animes/update/:id (Actualizar anime por id)
        @PutMapping("/update/{id}")
        public ResponseEntity<Info> editAnime(@PathVariable long id, @RequestBody Info info) {
            Optional<Info> anime = infoService.getAnimeById(id);

            if (anime.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Info edit = anime.get();
            edit.setName(info.getName());
            edit.setDescription(info.getDescription());
            edit.setReleaseDate(info.getReleaseDate());

            Info updatedAnime = infoService.saveAnime(edit);
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
