//package treinoDieta.api.infra.security;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTCreationException;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.exceptions.TokenExpiredException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import org.springframework.stereotype.Service;
//import treinoDieta.api.physicalEntities.usuario.Usuario;
//
//@Service
//public class TokenService {
//
//    public String gerarToken(Usuario usuario){
//        try {
//            Algorithm algorithm = Algorithm.HMAC256("1234");
//            String token = JWT.create()
//                    .withIssuer("auth0")
//                    .withSubject(usuario.getUsername())
//                    .withClaim("id", usuario.getId())
//                    .withClaim("authority", usuario.getAuthorities().stream().toString())
//                    .sign(algorithm);
//            return token;
//        } catch (JWTCreationException exception){
//            // Invalid Signing configuration / Couldn't convert Claims.
//        }
//
//        return null;
//    }
//
//    public String getSubject(String token){
//        DecodedJWT decodedJWT;
//        try {
//            Algorithm algorithm = Algorithm.HMAC256("1234");
//            return JWT.require(algorithm)
//                    // specify any specific claim validations
//                    .withIssuer("auth0")
//                    .build().verify(token)
//                    .getSubject();
//        } catch (JWTVerificationException exception){
//
//        }
//
//        return null;
//    }
//}
