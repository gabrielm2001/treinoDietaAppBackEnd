package treinoDieta.api.nonPhysicalEntities.ficha;

import jakarta.validation.constraints.NotBlank;
import treinoDieta.api.physicalEntities.professor.DadosDetalhamentoProfessor;

public record DadosDetalhamentoFicha(
        @NotBlank String nomeFicha,
        @NotBlank Long id
){
    public DadosDetalhamentoFicha(Ficha ficha){
        this(ficha.getNomeFicha(),ficha.getId());
    }
}
