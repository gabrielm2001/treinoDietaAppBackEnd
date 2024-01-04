package treinoDieta.api.aluno;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import treinoDieta.api.professor.Professor;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Page<Aluno> findAllByAtivoTrue(Pageable paginacao);

    @Query("SELECT a FROM Aluno a WHERE a.professor.id = :id and a.ativo = true")
    Page<DadosDetalhamentoAluno> findAllByProfessorIdAndAtivoTrue(Pageable paginacao, Long id);
}
