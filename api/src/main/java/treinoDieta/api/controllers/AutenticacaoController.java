package treinoDieta.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import treinoDieta.api.infra.security.DadosTokenJwt;
import treinoDieta.api.infra.security.TokenService;
import treinoDieta.api.physicalEntities.usuario.DadosLoginUsuario;
import treinoDieta.api.physicalEntities.usuario.DadosResgistroUsuario;
import treinoDieta.api.physicalEntities.usuario.Usuario;
import treinoDieta.api.physicalEntities.usuario.UsuarioRepository;

@RequestMapping("/auth")
@RestController
public class AutenticacaoController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity login(@RequestBody @Valid DadosLoginUsuario dados){
        if (usuarioRepository.findByLogin(dados.login()) == null){
            throw new RuntimeException("Usuario não encontrado");
        }

        var usuario = usuarioRepository.findByLogin(dados.login());

        var senhasCoincidem = passwordEncoder.matches(dados.senha(), usuario.getPassword());

        if (!senhasCoincidem){
            throw new RuntimeException("Senhas não coencidem");
        }


        var auth = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

        var tokenJwt = tokenService.GerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok().body(new DadosTokenJwt(tokenJwt));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DadosResgistroUsuario dados){
        if (usuarioRepository.findByLogin(dados.login()) != null){
            throw new RuntimeException("Usuario já existe");
        }

        var senhaCriptografada = new BCryptPasswordEncoder().encode(dados.senha());
        var usuario = new Usuario(dados, senhaCriptografada);

        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }

}
