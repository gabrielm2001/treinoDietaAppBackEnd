package treinoDieta.api.nonPhysicalEntities.treino;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import treinoDieta.api.nonPhysicalEntities.treino.Treino;

public interface TreinoRepository extends JpaRepository<Treino, Long> {
    Page<Treino> findAllByAtivoTrue(Pageable pageable);

    Page<Treino> findAllByAtivoTrueAndFichaId(Pageable paginacao, Long id);
}
