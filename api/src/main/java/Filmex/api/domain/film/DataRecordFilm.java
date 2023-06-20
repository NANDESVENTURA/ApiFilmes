package Filmex.api.domain.film;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRecordFilm(
        @NotBlank
        String name,

        @NotNull
        Platform platform,

        @NotNull
        Gender gender
) {
}
