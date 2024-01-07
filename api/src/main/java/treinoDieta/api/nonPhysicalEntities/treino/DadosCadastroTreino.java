package treinoDieta.api.nonPhysicalEntities.treino;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTreino(
        @NotBlank String nomeTreino

) {
}
