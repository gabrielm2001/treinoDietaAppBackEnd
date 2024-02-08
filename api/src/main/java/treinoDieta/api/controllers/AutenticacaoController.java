package treinoDieta.api.controllers;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity login(DadosLoginUsuario dados){
        if (usuarioRepository.findByLogin(dados.login()) == null){
            throw new RuntimeException("Usuario não encontrado");
        }

        var usuario = usuarioRepository.findByLogin(dados.login());

        var senhasCoincidem = passwordEncoder.matches(dados.senha(), usuario.getPassword());

        if (!senhasCoincidem){
            throw new RuntimeException("Senhas não coencidem");
        }

        var tokenJwt = tokenService.GerarToken((Usuario) usuario);

        return ResponseEntity.ok().body(tokenJwt);
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity registrar(DadosResgistroUsuario dados){
        if (usuarioRepository.findByLogin(dados.login()) != null){
            throw new RuntimeException("Usuario já existe");
        }

        var senhaCriptografada = new BCryptPasswordEncoder().encode(dados.senha());
        var usuario = new Usuario(dados, senhaCriptografada);

        return ResponseEntity.ok().build();
    }


}
