package treinoDieta.api.physicalEntities.usuario;

public record DadosResgistroUsuario(
        String login,
        String senha,
        UsuarioRole role
) {
}
