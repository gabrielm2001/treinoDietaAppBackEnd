package treinoDieta.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import treinoDieta.api.nonPhysicalEntities.exercicio.DadosCadastroExercicio;
import treinoDieta.api.nonPhysicalEntities.exercicio.DadosDetalhamentoExercicio;
import treinoDieta.api.nonPhysicalEntities.exercicio.Exercicio;
import treinoDieta.api.nonPhysicalEntities.exercicio.ExercicioRepository;
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
}
