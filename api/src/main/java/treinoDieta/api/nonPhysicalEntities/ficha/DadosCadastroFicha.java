package treinoDieta.api.nonPhysicalEntities.ficha;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroFicha(
        @NotBlank String nome_ficha

) {
}
