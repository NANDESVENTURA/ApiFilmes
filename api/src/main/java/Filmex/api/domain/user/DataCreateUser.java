package Filmex.api.domain.user;

import jakarta.validation.constraints.NotBlank;

public record DataCreateUser(
        
        @NotBlank
        String name,
        
        @NotBlank
        String password
) {

}
