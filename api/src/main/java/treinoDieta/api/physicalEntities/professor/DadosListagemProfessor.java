package treinoDieta.api.physicalEntities.professor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosListagemProfessor(@NotBlank String nome,
                                     @NotBlank @Pattern(regexp = "\\d{2}") String idade,
                                     @NotBlank String altura,
                                     @NotBlank @Email String email,
                                        @NotBlank Boolean ativo
) {

    public DadosListagemProfessor(Professor professor){
        this(professor.getNome(), professor.getIdade(),professor.getAltura(), professor.getEmail(), professor.getAtivo());
    }
}
