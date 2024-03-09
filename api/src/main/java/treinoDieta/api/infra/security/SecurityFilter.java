//package treinoDieta.api.infra.security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.hibernate.annotations.Filter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import treinoDieta.api.physicalEntities.usuario.UsuarioRepository;
//import treinoDieta.api.security.token.TokenService;
//
//import java.io.IOException;
//
//@Component
//public class SecurityFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private TokenService tokenService;
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        var token = getSubject(request);
//        if (token != null){
//            var subject = tokenService.getSubject(token);
//            System.out.println(subject);
//            var usuario = usuarioRepository.findByUsername(subject);
//            var tokenJwt = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
//
//            SecurityContextHolder.getContext().setAuthentication(tokenJwt);
//        }
//
//
//        filterChain.doFilter(request, response);
//    }
//
//    private String getSubject(HttpServletRequest request){
//
//        var token = request.getHeader("Authorization");
//
//        if (token != null){
//            return token.split("\\s")[1];
//        }
//
//        return null;
//    }
//}
