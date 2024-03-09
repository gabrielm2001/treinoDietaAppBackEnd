package treinoDieta.api.nonPhysicalEntities.treino;

import jakarta.validation.constraints.NotBlank;
import treinoDieta.api.nonPhysicalEntities.ficha.Ficha;

public record DadosDetalhamentoTreino(
        @NotBlank String nomeTreino,
        @NotBlank Long id
){
    public DadosDetalhamentoTreino(Treino treino){
        this(treino.getNomeTreino(), treino.getId());
    }
}
