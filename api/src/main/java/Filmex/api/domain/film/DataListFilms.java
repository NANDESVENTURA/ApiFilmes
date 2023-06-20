package Filmex.api.domain.film;

public record DataListFilms(Long id, String name, Platform platform, Gender gender) {

    public DataListFilms(Film film){
        this(film.getId(),film.getName(),film.getPlatform(),film.getGender());
    }
}
