package treinoDieta.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import treinoDieta.api.nonPhysicalEntities.ficha.*;
import treinoDieta.api.nonPhysicalEntities.treino.DadosDetalhamentoTreino;
import treinoDieta.api.nonPhysicalEntities.treino.TreinoRepository;
import treinoDieta.api.physicalEntities.aluno.AlunoRepository;

@RestController
@RequestMapping("/ficha")
@Tag(name = "Ficha", description = "Controlador da Ficha de Treino")
@SecurityRequirement(name = "bearer-key")
public class FichaController {

    @Autowired
    private FichaRepository fichaRepository;

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private TreinoRepository treinoRepository;

    @PostMapping("/{id}")
    @Transactional
    @Operation(summary = "Registra uma ficha", description = "Registra uma ficha")
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroFicha dados, @PathVariable Long id, UriComponentsBuilder uriBuilder){
        var aluno = alunoRepository.getReferenceById(id);
        var ficha = fichaRepository.save(new Ficha(dados, aluno));
        var uri = uriBuilder.path("/ficha/{id}").buildAndExpand(ficha.getId()).toUri();

        return  ResponseEntity.created(uri).body(new DadosDetalhamentoFicha(ficha));
    }

    @GetMapping
    @Operation(summary = "Paginação de todos os treinos", description = "Paginação de todos os treinos")
    public ResponseEntity<Page<DadosDetalhamentoFicha>> todasFichas(@PageableDefault(size = 10) Pageable paginacao){
        var page = fichaRepository.findAll(paginacao).map(DadosDetalhamentoFicha::new);

        return ResponseEntity.ok().body(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "Retorna uma ficha", description = "Retorna uma ficha")
    public ResponseEntity ficha(@PathVariable Long id){
        var ficha = fichaRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DadosDetalhamentoFicha(ficha));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Atualiza uma ficha", description = "Atualiza uma ficha")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody DadosAtualizacaoFicha dados){
        var ficha = fichaRepository.getReferenceById(id);
        ficha.atualizar(dados);

        return ResponseEntity.ok().body(new DadosDetalhamentoFicha(ficha));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Remove uma ficha", description = "Remove uma ficha")
    public ResponseEntity deletar(@PathVariable Long id){
        var ficha = fichaRepository.getReferenceById(id);
        ficha.deletar();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/treinos/{id}")
    @Operation(summary = "Paginação que retorna todos os treinos de uma ficha", description = "Paginação que retorna todos os treinos de uma ficha")
    public ResponseEntity<Page<DadosDetalhamentoTreino>> listarTodosExercicios(@PathVariable Long id, @PageableDefault(size = 10) Pageable paginacao){
        var page = treinoRepository.findAllByAtivoTrueAndFichaId(paginacao, id).map(DadosDetalhamentoTreino:: new);

        return ResponseEntity.ok().body(page);
    }

}
