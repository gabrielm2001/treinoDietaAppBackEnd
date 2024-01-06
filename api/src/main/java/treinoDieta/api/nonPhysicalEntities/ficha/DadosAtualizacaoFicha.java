package treinoDieta.api.nonPhysicalEntities.ficha;

import jakarta.validation.constraints.NotBlank;
import treinoDieta.api.physicalEntities.aluno.Aluno;

public record DadosAtualizacaoFicha(
        @NotBlank String nomeFicha
) {
    public DadosAtualizacaoFicha(Ficha ficha) {
        this(ficha.getNomeFicha());
    }
}
