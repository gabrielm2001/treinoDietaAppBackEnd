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
import treinoDieta.api.nonPhysicalEntities.ficha.FichaRepository;
import treinoDieta.api.nonPhysicalEntities.treino.TreinoRepository;
import treinoDieta.api.nonPhysicalEntities.treino.DadosAtualizacaotreino;
import treinoDieta.api.nonPhysicalEntities.treino.DadosCadastroTreino;
import treinoDieta.api.nonPhysicalEntities.treino.DadosDetalhamentoTreino;
import treinoDieta.api.nonPhysicalEntities.treino.Treino;

@RestController
@RequestMapping("/treino")
@Tag(name = "Treino", description = "Controlador do Treino")
@SecurityRequirement(name = "bearer-key")
public class TreinoController {
    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private FichaRepository fichaRepository;

    @PostMapping("/{id}")
    @Transactional
    @Operation(summary = "Regista um Treino", description = "Regista um Treino")
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTreino dados, @PathVariable Long id, UriComponentsBuilder uriBuilder){
        var ficha = fichaRepository.getReferenceById(id);
        var treino = new Treino(dados, ficha);

        treinoRepository.save(treino);

        var uri = uriBuilder.path("/treino/{id}").buildAndExpand(ficha.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTreino(treino));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um treino", description = "Retorna um treino")
    public ResponseEntity listar(@PathVariable Long id){
        var treino = treinoRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DadosDetalhamentoTreino(treino));
    }

    @GetMapping
    @Operation(summary = "Paginação de todos os treinos", description = "Paginação de todos os treinos")
    public ResponseEntity<Page<DadosDetalhamentoTreino>> listarTodos(@PageableDefault(size = 10) Pageable paginacao){
        var page = treinoRepository.findAllByAtivoTrue(paginacao).map(DadosDetalhamentoTreino:: new);

        return ResponseEntity.ok().body(page);
    }

    @PutMapping("{id}")
    @Transactional
    @Operation(summary = "Atualização de um treino", description = "Atualização de um treino")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaotreino nomeTreino){
        var treino = treinoRepository.getReferenceById(id);
        treino.update(nomeTreino);

        return ResponseEntity.ok().body(new DadosDetalhamentoTreino(treino));
    }

    @DeleteMapping("{id}")
    @Transactional@Operation(summary = "Remove um treino", description = "Remove um treino")
    public ResponseEntity deletar(@PathVariable Long id){
        var treino = treinoRepository.getReferenceById(id);
        treino.remover();

        return  ResponseEntity.noContent().build();
    }

}