package treinoDieta.api.professor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroProfessor(@NotBlank String nome,
                                     @NotBlank String idade,
                                     @NotBlank String altura,
                                     @NotBlank @Email String email
) {
}
