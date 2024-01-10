package treinoDieta.api.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import treinoDieta.api.nonPhysicalEntities.exercicio.*;
import treinoDieta.api.nonPhysicalEntities.ficha.TreinoRepository;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {
    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private TreinoRepository treinoRepository;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity cadastrar(@PathVariable Long id, @RequestBody DadosCadastroExercicio dados, UriComponentsBuilder uriBuilder){
        var ficha = treinoRepository.getReferenceById(id);
        var exercicio = new Exercicio(dados, ficha);
        exercicioRepository.save(exercicio);
        var uri = uriBuilder.path("/exercicio/{id}").buildAndExpand(exercicio.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoExercicio(exercicio));
    }

    @GetMapping("treino/{id}")
    public ResponseEntity<Page<DadosDetalhamentoExercicio>> listagemExerciciosTreino(@PathVariable Long id, Pageable paginacao){
        var treino = treinoRepository.getReferenceById(id);
        var page = exercicioRepository.findAllByAtivoTrueAndTreinoId(paginacao, id).map(DadosDetalhamentoExercicio::new);

        return ResponseEntity.ok().body(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody DadosAtualizacaoExercicio dados){
        var exercicio = exercicioRepository.getReferenceById(id);
        exercicio.atualizar(dados);
        return ResponseEntity.ok().body(new DadosDetalhamentoExercicio(exercicio));
    }
}
