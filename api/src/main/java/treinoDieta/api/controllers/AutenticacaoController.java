package treinoDieta.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import treinoDieta.api.physicalEntities.usuario.DadosRegistroUsuario;
import treinoDieta.api.physicalEntities.usuario.Usuario;
import treinoDieta.api.physicalEntities.usuario.UsuarioRepository;
import treinoDieta.api.security.token.DadosToken;
import treinoDieta.api.security.token.TokenService;

@RequestMapping("/auth")
@RestController
public class AutenticacaoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody DadosRegistroUsuario dados){
        if (usuarioRepository.existsByUsername(dados.username())){
            throw new RuntimeException("Usuario ja existe");
        }

        var usuario = new Usuario(dados);

        var tokenJwt = tokenService.gerarToken(usuario);

        var criptPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
        System.out.println(usuario);
        usuario.setPassword(criptPassword);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().body(new DadosToken(tokenJwt));
    }
}
