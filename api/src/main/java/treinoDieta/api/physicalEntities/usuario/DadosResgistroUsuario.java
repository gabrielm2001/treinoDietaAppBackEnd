package treinoDieta.api.physicalEntities.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosResgistroUsuario(
        @NotNull
        UserRole userRole,
        @NotNull
        String username,
        @NotNull
        String password
) {
}
