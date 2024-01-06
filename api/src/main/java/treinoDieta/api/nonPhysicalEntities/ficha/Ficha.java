package treinoDieta.api.nonPhysicalEntities.ficha;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import treinoDieta.api.physicalEntities.aluno.Aluno;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "Ficha")
@Table(name="fichas")
@EqualsAndHashCode(of = "id")
public class Ficha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    private String nomeFicha;


    public Ficha(DadosCadastroFicha dados, Aluno aluno){
        this.nomeFicha = dados.nomeFicha();
        this.aluno = aluno;
    }
}
