package treinoDieta.api.physicalEntities.aluno;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroAluno(
        @NotBlank @Email String email,
        @NotBlank String idade,
        @NotBlank String nome,
        @NotBlank String altura,
        @NotBlank String peso,
        @NotBlank String projeto,
        @NotNull Objetivo objetivo,
        @NotNull Genero genero
) {
}
