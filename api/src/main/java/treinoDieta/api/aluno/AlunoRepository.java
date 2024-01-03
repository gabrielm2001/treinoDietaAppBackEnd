package treinoDieta.api.aluno;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import treinoDieta.api.professor.Professor;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Page<Aluno> findAllByAtivoTrue(Pageable paginacao);
}
