package Filmex.api.domain.film;

import jakarta.validation.constraints.NotNull;

public record DataUpdateWatchingFilm(
        @NotNull
        Long id,
        Boolean watched,
        Note note
) {

}
