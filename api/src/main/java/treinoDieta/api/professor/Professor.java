package treinoDieta.api.professor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    public Professor(DadosCadastroProfessor dados){
        this.nome = dados.nome();
        this.idade = dados.idade();
        this.altura = dados.altura();
        this.email = dados.email();
    }
}
