package treinoDieta.api.nonPhysicalEntities.exercicio;

import jakarta.validation.constraints.Pattern;

public record DadosCadastroExercicio(
        @Pattern(regexp = "\\d{2}") String series,
        @Pattern(regexp = "\\d{2}") String repeticoes,
        String tecnica,
        String detalhes,
        String nomeExercicio
) {
}
