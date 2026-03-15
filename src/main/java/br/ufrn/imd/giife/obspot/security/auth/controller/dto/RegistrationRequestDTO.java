package br.ufrn.imd.giife.obspot.security.auth.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationRequestDTO(
        @NotBlank(message = "O nome não pode estar em branco.")
        @Schema(example = "Emanuel Kywal")
        String nome,

        @NotBlank(message = "O sobrenome não pode estar em branco.")
        @Schema(example = "Cabral")
        String sobrenome,

        @Email(message = "O formato do email é inválido.")
        @NotBlank(message = "O email não pode estar em branco.")
        @Schema(example = "kywal@outlook.com")
        String email,

        @NotBlank(message = "A senha não pode estar em branco.")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
        @Schema(example = "12345678")
        String password,

        @NotBlank(message = "As senhas devem ser iguais.")
        @Size(min = 8, message = "As senhas devem ser iguais.")
        @Schema(example = "12345678")
        String confirmPassword
) {
}
