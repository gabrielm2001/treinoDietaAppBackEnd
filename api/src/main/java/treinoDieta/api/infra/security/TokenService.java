package treinoDieta.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import treinoDieta.api.physicalEntities.usuario.Usuario;

@Service
public class TokenService {

    @Value("{api.security.token.secret}")
    private String secret;

    public String GerarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("dietaTreino_api")
                    .withSubject(usuario.getLogin())
                    .withClaim("autoridade", usuario.getAuthorities().toString())
                    .withClaim("senha", usuario.getSenha())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Token inválido");
        }
    }

    public String getSubject(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("dietaTreino_api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token inválido ou expirado");
        }

    }

}
