package treinoDieta.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import treinoDieta.api.physicalEntities.usuario.DadosLoginUsuario;
import treinoDieta.api.physicalEntities.usuario.DadosResgistroUsuario;
import treinoDieta.api.physicalEntities.usuario.Usuario;
import treinoDieta.api.physicalEntities.usuario.UsuarioRepository;
import treinoDieta.api.infra.security.token.DadosToken;
import treinoDieta.api.infra.security.token.TokenService;

@RequestMapping("/auth")
@RestController
public class AutenticacaoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<Object> register(@RequestBody @Valid DadosResgistroUsuario dados){
        if (usuarioRepository.existsByUsername(dados.username())){
            throw new RuntimeException("Usuario ja existe");
        }

        var usuario = new Usuario(dados);

        var criptPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
        usuario.setPassword(criptPassword);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<Object> register(@RequestBody @Valid DadosLoginUsuario dados){

        var usuario = usuarioRepository.findByUsername(dados.username());

        if (!usuarioRepository.existsByUsername(dados.username())){
            throw new RuntimeException("Usuario não existe");
        }
        System.out.println(dados);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isPasswordMatch = passwordEncoder.matches(dados.password(), usuario.getPassword());

        if(!isPasswordMatch){
            throw new RuntimeException("Usuario ou senha incorretos");
        };

        var token = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        var tokenJwt = tokenService.gerarToken((Usuario) token.getPrincipal());

        return ResponseEntity.ok().body(new DadosToken(tokenJwt));
    }
}
