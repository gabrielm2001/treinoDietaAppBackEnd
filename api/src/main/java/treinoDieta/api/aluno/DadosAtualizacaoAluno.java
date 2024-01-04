package treinoDieta.api.aluno;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAluno(
                                    @NotNull Genero genero,
                                    @NotBlank String nome,
                                    @NotBlank String idade,
                                    @NotBlank String altura,
                                    @NotBlank String peso,
                                    @NotBlank String projeto,
                                    @NotNull Objetivo objetivo) {


    public DadosAtualizacaoAluno(Aluno aluno) {
        this( aluno.getGenero(), aluno.getNome(),aluno.getIdade(), aluno.getAltura(), aluno.getPeso(), aluno.getProjeto(), aluno.getObjetivo());
    }
}
