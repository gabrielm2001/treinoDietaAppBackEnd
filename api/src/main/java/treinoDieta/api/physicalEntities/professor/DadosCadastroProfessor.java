package treinoDieta.api.physicalEntities.professor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroProfessor(@NotBlank String nome,
                                     @NotBlank @Pattern(regexp = "\\d{2}") String idade,
                                     @NotBlank String altura,
                                     @NotBlank @Email String email
) {
}
