package treinoDieta.api.physicalEntities.aluno;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosDetalhamentoAluno(
        @NotBlank String nome,
        @NotBlank String email,
        @NotBlank String idade,
        @NotBlank String altura,
        @NotBlank String peso,
        @NotBlank String projeto,
        @NotNull Objetivo objetivo,
        @NotBlank String agua,
        @NotBlank String tbm,
        @NotBlank Long id
) {
    public DadosDetalhamentoAluno(Aluno aluno) {
        this(aluno.getNome(),aluno.getEmail(), aluno.getIdade(), aluno.getAltura(), aluno.getPeso(), aluno.getProjeto(), aluno.getObjetivo(), aluno.getAgua(), aluno.getTbm(), aluno.getId());
    }
}
