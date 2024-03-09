package treinoDieta.api.physicalEntities.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    UserDetails findByUsername(String username);

    boolean existsByUsername(String username);

    UserDetails getReferenceByUsername(String username);
}
