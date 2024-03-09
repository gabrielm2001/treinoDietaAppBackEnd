package treinoDieta.api.physicalEntities.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosLoginUsuario(
        @NotNull
        String username,
        @NotNull
        String password
) {
}
