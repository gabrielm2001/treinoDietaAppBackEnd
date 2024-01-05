package treinoDieta.api.nonPhysicalEntities.ficha;

import jakarta.validation.constraints.NotBlank;
import treinoDieta.api.physicalEntities.professor.DadosDetalhamentoProfessor;

public record DadosDetalhamentoFicha(
        @NotBlank String nome_ficha
){
    public DadosDetalhamentoFicha(Ficha ficha){
        this(ficha.getNome_ficha());
    }
}
