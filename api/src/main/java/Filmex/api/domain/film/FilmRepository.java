package Filmex.api.domain.film;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Long> {
    Film findByName(String name);
    Optional<Film> findById(Long id);
}
