package treinoDieta.api.physicalEntities.aluno;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroAluno(
        @Email
        @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "Email inválido")
        @NotBlank @Email String email,
        @Pattern(regexp = "\\d{2,4}", message = "Idade inválida") @NotBlank String idade,
        @NotBlank String nome,
        @Pattern(regexp = "\\d{2,4}", message = "A altura deve estar em centímetros") @NotBlank String altura,
        @Pattern(regexp = "\\d{2,4}", message = "O peso deve estar em quilos") @NotBlank String peso,
        @NotBlank String projeto,
        @NotNull Objetivo objetivo,
        @NotNull Genero genero
) {
}
