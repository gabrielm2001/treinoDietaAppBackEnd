package treinoDieta.api.nonPhysicalEntities.exercicio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
    Page<Exercicio> findAllByAtivoTrueAndTreinoId(Pageable paginacao, Long id);
}
