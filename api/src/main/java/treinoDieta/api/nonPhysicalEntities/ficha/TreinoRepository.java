package treinoDieta.api.nonPhysicalEntities.ficha;

import org.springframework.data.jpa.repository.JpaRepository;
import treinoDieta.api.nonPhysicalEntities.treino.Treino;

public interface TreinoRepository extends JpaRepository<Treino, Long> {
}
