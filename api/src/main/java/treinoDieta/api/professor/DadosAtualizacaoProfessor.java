package treinoDieta.api.professor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoProfessor(@NotBlank String nome,
                                        @NotBlank @Pattern(regexp = "\\d{2}") String idade,
                                        @NotBlank String altura
                                        ){

    public DadosAtualizacaoProfessor(Professor professor){
        this(professor.getNome(), professor.getIdade(),professor.getAltura());
    }
}
