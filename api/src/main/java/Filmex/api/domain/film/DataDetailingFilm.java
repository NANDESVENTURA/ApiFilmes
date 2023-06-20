package Filmex.api.domain.film;

public record DataDetailingFilm(Long id, String name,Platform platform, Gender gender, Boolean watched, Note note) {
    public DataDetailingFilm(Film film) {
        this(film.getId(), film.getName(), film.getPlatform(), film.getGender(), film.getWatched(), film.getNote());
    }
}
