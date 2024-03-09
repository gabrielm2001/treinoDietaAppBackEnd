package treinoDieta.api.nonPhysicalEntities.treino;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaotreino(@NotBlank String nomeTreino) {
    public DadosAtualizacaotreino(DadosAtualizacaotreino dados){
        this(dados.nomeTreino);
    }
}
