package Filmex.api.domain.film;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findByName(String name);
}
