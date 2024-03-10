package treinoDieta.api.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import treinoDieta.api.nonPhysicalEntities.exercicio.*;
import treinoDieta.api.nonPhysicalEntities.treino.TreinoRepository;

@RestController
@RequestMapping("/exercicios")
@Tag(name = "Exercicio", description = "Controlador do Exercicio")
@SecurityRequirement(name = "bearer-key")
public class ExercicioController {
    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private TreinoRepository treinoRepository;

    @PostMapping("/{id}")
    @Transactional
    @Operation(summary = "Registra um exercicio", description = "Registra um exercicio")
    public ResponseEntity cadastrar(@PathVariable Long id, @RequestBody DadosCadastroExercicio dados, UriComponentsBuilder uriBuilder){
        var ficha = treinoRepository.getReferenceById(id);
        var exercicio = new Exercicio(dados, ficha);
        exercicioRepository.save(exercicio);
        var uri = uriBuilder.path("/exercicio/{id}").buildAndExpand(exercicio.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoExercicio(exercicio));
    }

    @GetMapping("treino/{id}")
    @Operation(summary = "Paginação que retorna todos os exercicios de um treino", description = "Paginação que retorna todos os exercicios de um treino")
//    @Operation(summary = "Obtém dados de exemplo", description = "Este endpoint retorna dados de exemplo com base no parâmetro fornecido")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Dados de exemplo retornados com sucesso"),
//            @ApiResponse(responseCode = "400", description = "Parâmetro inválido fornecido")
//    })
    public ResponseEntity<Page<DadosDetalhamentoExercicio>> listagemExerciciosTreino(@PathVariable Long id, Pageable paginacao){
        var treino = treinoRepository.getReferenceById(id);
        var page = exercicioRepository.findAllByAtivoTrueAndTreinoId(paginacao, id).map(DadosDetalhamentoExercicio::new);

        return ResponseEntity.ok().body(page);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Atualiza um exercicio", description = "Atualiza um exercicio")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody DadosAtualizacaoExercicio dados){
        var exercicio = exercicioRepository.getReferenceById(id);
        exercicio.atualizar(dados);
        return ResponseEntity.ok().body(new DadosDetalhamentoExercicio(exercicio));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Remove um exercicio", description = "Remove um exercicio")
    public ResponseEntity deletar(@PathVariable Long id){
        var exercicio = exercicioRepository.getReferenceById(id);
        exercicio.deletar();
        return ResponseEntity.noContent().build();
    }
}
