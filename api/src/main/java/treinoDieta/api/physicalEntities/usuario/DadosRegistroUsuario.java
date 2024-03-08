package treinoDieta.api.physicalEntities.usuario;

public record DadosRegistroUsuario(
        UserRole userRole,
        String username,
        String password

) {
}
