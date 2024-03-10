package treinoDieta.api.physicalEntities.aluno;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import treinoDieta.api.physicalEntities.professor.DadosCadastroProfessor;
import treinoDieta.api.physicalEntities.professor.Professor;
import treinoDieta.api.physicalEntities.professor.ProfessorRepository;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver 0 alunos na página")
    void findAllByProfessorIdAndAtivoTrueCase1(){
        Pageable paginacao = null;
        var id = 223423L;
        var page = alunoRepository.findAllByProfessorIdAndAtivoTrue(paginacao, id);
        var numAlunos = page.getTotalElements();
        assertThat(numAlunos).isEqualTo(0L);
    }

    @Test
    @DisplayName("Deveria devolver 1 alunos na página")
    void findAllByProfessorIdAndAtivoTrueCase2(){

        var professor = cadastrarProfessor("Gabriel", "22", "185", "gabriel@gmail.com");
        var aluno = cadastrarAluno("gabbriekl@gmail.com", "22", "gabbbriel", "187", "84", "king", Objetivo.PERDER_PESO, Genero.MASCULINO);

        aluno.setProfessor(professor);

        Pageable paginacao = null;
        var page = alunoRepository.findAllByProfessorIdAndAtivoTrue(paginacao, professor.getId());
        var numAlunos = page.getSize();
        assertThat(numAlunos).isEqualTo(1L);
    }

    @Test
    @DisplayName("Deveria devolver 1 aluno na página")
    void findAllByAtivoTrueCase1(){
        Pageable paginacao = null;
        var page = alunoRepository.findAllByAtivoTrue(paginacao);
        var numAlunos = page.getTotalElements();
        assertThat(numAlunos).isEqualTo(0L);
    }

    @Test
    @DisplayName("Deveria devolver 1 alunos na página")
    void findAllByAtivoTrueCase2(){
        var aluno = cadastrarAluno("gabbriekl@gmail.com", "22", "gabbbriel", "187", "84", "king", Objetivo.PERDER_PESO, Genero.MASCULINO);

        aluno.setAtivo(false);
        Pageable paginacao = null;
        var page = alunoRepository.findAllByAtivoTrue(paginacao);
        var numAlunos = page.getSize();
        assertThat(numAlunos).isEqualTo(0L);
    }

    private Professor cadastrarProfessor(
            @NotBlank String nome,
            @NotBlank @Pattern(regexp = "\\d{2}", message = "Idade inválida") String idade,
            @Pattern(regexp = "\\d{3}", message = "A altura deve estar em centímetros") @NotBlank String altura,
            @Email
            @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "Email inválido")
            @NotBlank @Email String email
    ){
        var professor = new Professor(dadosCadastroProfessor(nome, idade, altura, email));
        em.persist(professor);
        return professor;
    };

    private Aluno cadastrarAluno(
            @Email
            @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "Email inválido")
            @NotBlank @Email String email,
            @Pattern(regexp = "\\d{2,4}", message = "Idade inválida") @NotBlank String idade,
            @NotBlank String nome,
            @Pattern(regexp = "\\d{2,4}", message = "A altura deve estar em centímetros") @NotBlank String altura,
            @Pattern(regexp = "\\d{2,4}", message = "O peso deve estar em quilos") @NotBlank String peso,
            @NotBlank String projeto,
            @NotNull Objetivo objetivo,
            @NotNull Genero genero
    ){
        var aluno = new Aluno(dadosCadastroAluno(email, idade, nome, altura, peso, projeto, objetivo, genero));
        em.persist(aluno);
        return aluno;
    }

    private DadosCadastroAluno dadosCadastroAluno(String email, String idade, String nome, String altura, String peso, String projeto, Objetivo objetivo, Genero genero){
        return new DadosCadastroAluno(
                "gabriel@gmail.com",
                "22",
                "Gabriel",
                "185",
                "84",
                "King",
                Objetivo.PERDER_PESO,
                Genero.MASCULINO
        );
    }

    private DadosCadastroProfessor dadosCadastroProfessor(String nome, String idade, String altura, String email){
        return new DadosCadastroProfessor(
                "gabriell",
                "22",
                "185",
                "gabrieljj@gmail.com"
        );
    }
}