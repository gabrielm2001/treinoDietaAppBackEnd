package treinoDieta.api.nonPhysicalEntities.exercicio;

import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoExercicio(
        @Pattern(regexp = "\\d{2}") String series,
        @Pattern(regexp = "\\d{2}") String repeticoes,
        String tecnica,
        String detalhes,
        String nomeExercicio
) {
    public DadosAtualizacaoExercicio(Exercicio exercicio){
        this(exercicio.getSeries(), exercicio.getRepeticoes(), exercicio.getTecnica(), exercicio.getDetalhes(), exercicio.getNomeExercicio());
    }
}
