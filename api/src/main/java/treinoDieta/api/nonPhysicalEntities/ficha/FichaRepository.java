package treinoDieta.api.nonPhysicalEntities.ficha;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichaRepository extends JpaRepository<Ficha, Long> {

    Page<Ficha> findAllByAluno_id(Pageable pageable, Long alunoId);
}
