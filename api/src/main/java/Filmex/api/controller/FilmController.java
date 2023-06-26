package Filmex.api.controller;

import Filmex.api.domain.film.*;
import Filmex.api.domain.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRecordFilm data, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal User logged) throws NoSuchAlgorithmException {
        try {
            Film filmsExists = repository.findByNameAndUser(data.name(), logged.getId());
            if (filmsExists != null) {
                HashMap<String, String> ErrorMessage = new HashMap<>();
                ErrorMessage.put("ErrorMessage", "Film already registered");
                return ResponseEntity.badRequest().body(ErrorMessage);
            }
            var film = new Film(data,logged);
            repository.save(film);

            var uri = uriBuilder.path("/films/{id}").buildAndExpand(film.getId()).toUri();
            return ResponseEntity.created(uri).body(new DataDetailingFilm(film));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping()
    public Page<DataListFilms> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination, @AuthenticationPrincipal User logged) {
        try {
            return repository.findAll(pagination).map(DataListFilms::new);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Internal Error - Could not complete", "Internal Error", JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }

    @GetMapping("/userid")
    public List<Film> listByUser(@RequestParam(required = false, name = "watched") Boolean watched,@AuthenticationPrincipal User logged) {
        try {
            System.out.println(watched);
            if (watched != null) return repository.findByUserIdAndWatched(logged.getId(), watched);
            return repository.findByUserId(logged.getId());

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Internal Error - Could not complete", "Internal Error", JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        try {
            Optional<Film> filmsExists = repository.findById(id);
            if (filmsExists.isEmpty()) {
                HashMap<String, String> ErrorMessage = new HashMap<>();
                ErrorMessage.put("ErrorMessage", "Film not found");
                return ResponseEntity.badRequest().body(ErrorMessage);
            }
            var film = repository.getReferenceById(id);
            return ResponseEntity.ok(new DataDetailingFilm(film));
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Internal Error - Could not complete", "Internal Error", JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }


    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataUpdateFilm data) {
        try {
            Film filmsExists = repository.findByName(data.name());
            if (filmsExists != null) {
                var film = repository.getReferenceById(data.id());
                film.updateData(data);

                return ResponseEntity.ok(new DataDetailingFilm(film));
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Internal Error - Could not complete", "Internal Error", JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }

    @PutMapping
    @RequestMapping("/watching")
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataUpdateWatchingFilm data) {
        try {
            var film = repository.getReferenceById(data.id());
            film.updateWatchingData(data);

            return ResponseEntity.ok(new DataDetailingFilm(film));
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Internal Error - Could not complete", "Internal Error", JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id, @AuthenticationPrincipal User logged ) {
        try {
            var filmFound = repository.findFilmByIdAndUserid(id, logged.getId());
            if (filmFound == null) {
                HashMap<String, String> DeleteMessage = new HashMap<>();
                DeleteMessage.put("Not Found", "Movie not found");
                return ResponseEntity.badRequest().body(DeleteMessage);
            }
            HashMap<String, String> DeleteMessage = new HashMap<>();
            DeleteMessage.put("DeleteMessage", "Successfully deleted movie");
            repository.deleteById(id);
            return ResponseEntity.ok().body(DeleteMessage);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Internal Error - Could not complete", "Internal Error", JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }
}