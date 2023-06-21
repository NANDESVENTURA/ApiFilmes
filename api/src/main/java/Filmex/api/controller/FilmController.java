package Filmex.api.controller;

import Filmex.api.domain.film.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRecordFilm data, UriComponentsBuilder uriBuilder) throws NoSuchAlgorithmException {
        List<Film> filmsExists = repository.findByName(data.name());
        if(filmsExists.size() > 0){ return null;}
            var film = new Film(data);
            repository.save(film);

            var uri = uriBuilder.path("/films/{id}").buildAndExpand(film.getId()).toUri();
            return ResponseEntity.created(uri).body(new DataDetailingFilm(film));

    }

    @GetMapping()
    public Page<DataListFilms>list(@PageableDefault(size = 10, sort = {"name"})Pageable pagination) {
        return repository.findAll(pagination).map(DataListFilms::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity detailing(@PathVariable Long id) {
        var film = repository.getReferenceById(id);
        return ResponseEntity.ok(new DataListFilms(film));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update (@RequestBody @Valid DataUpdateFilm data) {
        var film =  repository.getReferenceById(data.id());
        film.updateData(data);

        return ResponseEntity.ok(new DataDetailingFilm(film));
    }

    @PutMapping
    @RequestMapping("/watching")
    @Transactional
    public ResponseEntity update (@RequestBody @Valid DataUpdateWatchingFilm data) {
        var film =  repository.getReferenceById(data.id());
        film.updateWatchingData(data);

        return ResponseEntity.ok(new DataDetailingFilm(film));
     }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
