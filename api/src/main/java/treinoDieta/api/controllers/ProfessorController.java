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
import treinoDieta.api.physicalEntities.aluno.AlunoRepository;
import treinoDieta.api.physicalEntities.aluno.DadosDetalhamentoAluno;
import treinoDieta.api.physicalEntities.professor.*;

@RestController
@RequestMapping("/professor")
@Tag(name = "Professor", description = "Controlador do Professor")
@SecurityRequirement(name = "bearer-key")
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Regista um Professor", description = "Regista um Professor")
    public ResponseEntity cadastrar(@Valid @RequestBody DadosCadastroProfessor dados, UriComponentsBuilder uriBuilder){
        var professor = professorRepository.save(new Professor(dados));
        var uri = uriBuilder.path("/professor/{id}").buildAndExpand(professor.getId()).toUri();

        return ResponseEntity.created(uri).body(professor);
    }

    @GetMapping
    @Operation(summary = "Paginação que retorna todos os professores", description = "Paginação que retorna todos os professores")
    public ResponseEntity<Page<DadosListagemProfessor>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page = professorRepository.findAllByAtivoTrue(paginacao).map(DadosListagemProfessor::new);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um professor", description = "Retorna um professor")
    public ResponseEntity listar1(@PathVariable Long id){
        var professor = professorRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoProfessor(professor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Retorna um professor", description = "Retorna um professor")
    public ResponseEntity deletar(@PathVariable Long id){
        var professor = professorRepository.getReferenceById(id);
        professor.deletar();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Atualiza um professor", description = "Atualiza um professor")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoProfessor dados){
        var professor = professorRepository.getReferenceByIdAndAtivoTrue(id);
        professor.atualizar(dados);

        return ResponseEntity.ok().body(new DadosDetalhamentoProfessor(professor));
    }

    @GetMapping("alunos/{id}")
    @Operation(summary = "Paginação que retorna todos os alunos de um professor", description = "Paginação que retorna todos os alunos de um professor")
    public ResponseEntity<Page<DadosDetalhamentoAluno>> alunosDoProfessor(@PageableDefault(size=10, sort = {"nome"}) Pageable paginacao, @PathVariable Long id){
        var alunosPage =  alunoRepository.findAllByProfessorIdAndAtivoTrue(paginacao, id);
        return ResponseEntity.status(200).body(alunosPage);
    }


}
