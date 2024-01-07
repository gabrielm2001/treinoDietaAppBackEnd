package treinoDieta.api.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import treinoDieta.api.nonPhysicalEntities.ficha.DadosDetalhamentoFicha;
import treinoDieta.api.nonPhysicalEntities.ficha.FichaRepository;
import treinoDieta.api.nonPhysicalEntities.ficha.TreinoRepository;
import treinoDieta.api.nonPhysicalEntities.treino.DadosCadastroTreino;
import treinoDieta.api.nonPhysicalEntities.treino.DadosDetalhamentoTreino;
import treinoDieta.api.nonPhysicalEntities.treino.Treino;

@RestController
@RequestMapping("/treino")
public class TreinoController {
    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private FichaRepository fichaRepository;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTreino dados, @PathVariable Long id, UriComponentsBuilder uriBuilder){
        var ficha = fichaRepository.getReferenceById(id);
        var treino = new Treino(dados, ficha);

        treinoRepository.save(treino);

        var uri = uriBuilder.path("/treino/{id}").buildAndExpand(ficha.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTreino(treino));
    }
}