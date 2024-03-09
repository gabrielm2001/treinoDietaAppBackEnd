package treinoDieta.api.nonPhysicalEntities.exercicio;

import jakarta.validation.constraints.Pattern;
import treinoDieta.api.nonPhysicalEntities.exercicio.Exercicio;

public record DadosDetalhamentoExercicio(
        @Pattern(regexp = "\\d{2}") String series,
        @Pattern(regexp = "\\d{2}") String repeticoes,
        String tecnica,
        String detalhes,
        String nomeExercicio
) {
    public  DadosDetalhamentoExercicio(Exercicio exercicio){
        this(exercicio.getSeries(), exercicio.getRepeticoes(), exercicio.getTecnica(), exercicio.getDetalhes(), exercicio.getNomeExercicio());
    }
}
