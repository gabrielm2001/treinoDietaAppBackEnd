package treinoDieta.api.nonPhysicalEntities.treino;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import treinoDieta.api.nonPhysicalEntities.ficha.Ficha;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "treinos")
@Entity(name = "Treino")
@EqualsAndHashCode(of = "id")
public class Treino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeTreino;

    @ManyToOne
    @JoinColumn(name = "ficha_id")
    private Ficha ficha;

    private boolean ativo;

    public Treino(DadosCadastroTreino dados, Ficha ficha){
        this.ativo = true;
        this.nomeTreino = dados.nomeTreino();
        this.ficha = ficha;
    }

    public void update(DadosAtualizacaotreino dados) {
        if (dados.nomeTreino() != null){
            System.out.println(dados.nomeTreino());
            this.nomeTreino = dados.nomeTreino();
        }
    }
}
