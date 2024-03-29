package treinoDieta.api.physicalEntities.professor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "professores")
@Entity(name = "Professor")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    private String idade;
    private String altura;
    private String email;

    private Boolean ativo;

    public Professor(DadosCadastroProfessor dados){
        this.nome = dados.nome();
        this.idade = dados.idade();
        this.altura = dados.altura();
        this.email = dados.email();
        this.ativo = true;
    }

    public void deletar() {
        this.ativo = Boolean.FALSE;
    }

    public void atualizar(DadosAtualizacaoProfessor dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.idade() != null){
            this.idade = dados.idade();
        }
        if(dados.altura() != null){
            this.altura = dados.altura();
        }
    }
}
