package Filmex.api.domain.film;

import jakarta.validation.constraints.NotNull;

public record DataUpdateFilm(

        @NotNull
        Long id,
        String name,
        Platform platform,
        Gender gender
) {
}
