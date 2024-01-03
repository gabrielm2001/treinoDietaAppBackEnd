package treinoDieta.api.aluno;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import treinoDieta.api.aluno.Aluno;
import treinoDieta.api.aluno.Objetivo;

public record DadosDetalhamentoAluno(
        @NotBlank String email,
        @NotBlank String idade,
        @NotBlank String altura,
        @NotBlank String peso,
        @NotBlank String projeto,
        @NotNull Objetivo objetivo,
        @NotBlank String agua,
        @NotBlank String tbm

) {
    public DadosDetalhamentoAluno(Aluno aluno) {
        this(aluno.getEmail(), aluno.getIdade(), aluno.getAltura(), aluno.getPeso(), aluno.getProjeto(), aluno.getObjetivo(), aluno.getAgua(), aluno.getTbm());
    }
}
