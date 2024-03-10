package treinoDieta.api.physicalEntities.aluno;
import jakarta.persistence.*;
import lombok.*;
import treinoDieta.api.physicalEntities.professor.Professor;

@Table(name ="alunos")
@Entity(name = "Aluno")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    private String nome;
    private String idade;
    private String peso;
    private String altura;
    private String email;
    private String projeto;

    @Enumerated(EnumType.STRING)
    private Objetivo objetivo;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    private String agua;
    private String tbm;
    private Boolean ativo;

    public Aluno(DadosCadastroAluno dados, Professor professor){
        this.nome = dados.nome();
        this.idade = dados.idade();
        this.altura = dados.altura();
        this.email = dados.email();
        this.peso = dados.peso();
        this.projeto = dados.projeto();
        this.objetivo = dados.objetivo();
        this.genero = dados.genero();
        this.ativo = true;

        this.agua = String.valueOf(Double.parseDouble(dados.peso()) * 35);
        this.tbm = calculaTbm(dados.genero(), dados.peso(), dados.idade(), dados.altura());
        this.professor = professor;
    }

    public Aluno(DadosCadastroAluno dados) {
        this.nome = dados.nome();
        this.idade = dados.idade();
        this.altura = dados.altura();
        this.email = dados.email();
        this.peso = dados.peso();
        this.projeto = dados.projeto();
        this.objetivo = dados.objetivo();
        this.genero = dados.genero();
        this.ativo = true;


        this.agua = String.valueOf(Double.parseDouble(dados.peso()) * 35);
        this.tbm = calculaTbm(dados.genero(), dados.peso(), dados.idade(), dados.altura());
        this.professor = null;
    }


    private String calculaTbm(Genero genero, String peso, String idade, String altura) {
        if (genero == Genero.MASCULINO || genero == Genero.NENHUM) {
            return String.valueOf(88.362 + (13.397 * Double.parseDouble(peso)) + (4.799 * Double.parseDouble(altura)) - (5.677 * Double.parseDouble(idade)));
        } else if (genero == Genero.FEMININO) {
            return String.valueOf(447.593 + (9.247 * Double.parseDouble(peso)) + (3.098 * Double.parseDouble(altura)) - (4.330 * Double.parseDouble(idade)));
        } else {
            throw new IllegalArgumentException("Invalid gender");
        }
    }

    public void atualizar(DadosAtualizacaoAluno dados) {
        if (dados.altura() != null){
            this.altura = dados.altura();
        }else if (dados.peso() != null){
            this.peso = dados.peso();
        }else if (dados.idade() != null){
            this.idade = dados.idade();
        }else if (dados.genero() != null){
            this.genero = dados.genero();
        }else if (dados.nome() != null){
            this.nome = dados.nome();
        }else if (dados.objetivo() != null){
            this.objetivo = dados.objetivo();
        }else if (dados.projeto() != null){
            this.projeto = dados.projeto();
        }
    }

    public void deletar() {
        this.ativo = false;
    }
}
