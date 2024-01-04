package treinoDieta.api.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import treinoDieta.api.aluno.AlunoRepository;
import treinoDieta.api.aluno.DadosDetalhamentoAluno;
import treinoDieta.api.professor.*;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@Valid @RequestBody DadosCadastroProfessor dados, UriComponentsBuilder uriBuilder){
        var professor = professorRepository.save(new Professor(dados));
        var uri = uriBuilder.path("/professor/{id}").buildAndExpand(professor.getId()).toUri();

        return ResponseEntity.created(uri).body(professor);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemProfessor>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page = professorRepository.findAllByAtivoTrue(paginacao).map(DadosListagemProfessor::new);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listar1(@PathVariable Long id){
        var professor = professorRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoProfessor(professor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var professor = professorRepository.getReferenceById(id);
        professor.deletar();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoProfessor dados){
        var professor = professorRepository.getReferenceByIdAndAtivoTrue(id);
        professor.atualizar(dados);

        return ResponseEntity.ok().body(new DadosDetalhamentoProfessor(professor));
    }

    @GetMapping("alunos/{id}")
    public ResponseEntity<Page<DadosDetalhamentoAluno>> alunosDoProfessor(@PageableDefault(size=10, sort = {"nome"}) Pageable paginacao, @PathVariable Long id){
        var alunosPage =  alunoRepository.findAllByProfessorIdAndAtivoTrue(paginacao, id);
        return ResponseEntity.status(200).body(alunosPage);
    }


}
