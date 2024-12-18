package be.ecotravel.back.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreationDto(
        @NotNull(message = "Email cannot be null")
        @NotBlank(message = "Email cannot be blank")
        String email,
        @NotNull(message = "Firstname cannot be null")
        @NotBlank(message = "Firstname cannot be blank")
        String firstname,
        @NotNull(message = "Lastname cannot be null")
        @NotBlank(message = "Lastname cannot be blank")
        String lastname,
        /*@NotNull(message = "Password cannot be null")
        @NotBlank(message = "Password cannot be blank")*/ //TODO Rajouter ceci une fois que le reste de putUser est fait
        String password
) {
}