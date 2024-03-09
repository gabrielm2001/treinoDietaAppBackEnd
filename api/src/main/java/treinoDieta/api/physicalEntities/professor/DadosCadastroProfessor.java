package treinoDieta.api.physicalEntities.professor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroProfessor(@NotBlank String nome,
                                     @NotBlank @Pattern(regexp = "\\d{2}", message = "Idade inválida") String idade,
                                     @Pattern(regexp = "\\d{3}", message = "A altura deve estar em centímetros") @NotBlank String altura,
                                     @Email
                                     @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "Email inválido")
                                     @NotBlank @Email String email
) {
}
