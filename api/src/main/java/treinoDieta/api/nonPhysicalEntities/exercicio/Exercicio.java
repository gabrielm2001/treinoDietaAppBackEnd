package treinoDieta.api.nonPhysicalEntities.exercicio;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import treinoDieta.api.nonPhysicalEntities.treino.Treino;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "exercicios")
@Entity( name = "Exercicio")
@EqualsAndHashCode(of = "id")
public class Exercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "treino_id")
    private Treino treino;

    private String series;
    private String repeticoes;
    private String tecnica;
    private String detalhes;
    private String nomeExercicio;
    private boolean ativo;

    public Exercicio(@Valid DadosCadastroExercicio dados, Treino treino){
        this.series = dados.series();
        this.repeticoes = dados.repeticoes();
        this.tecnica = dados.tecnica();
        this.detalhes = dados.detalhes();
        this.nomeExercicio = dados.nomeExercicio();
        this.treino = treino;
        this.ativo = true;
    }
}
