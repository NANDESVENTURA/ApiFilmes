package Filmex.api.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataCreateUser(
        
        @NotBlank
        String name,

        @NotNull
        String email,
        
        @NotBlank
        String password
) {
}
