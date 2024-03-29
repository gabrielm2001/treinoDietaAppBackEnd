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
import treinoDieta.api.nonPhysicalEntities.ficha.DadosCadastroFicha;
import treinoDieta.api.nonPhysicalEntities.ficha.DadosDetalhamentoFicha;
import treinoDieta.api.nonPhysicalEntities.ficha.FichaRepository;
import treinoDieta.api.physicalEntities.aluno.*;
import treinoDieta.api.physicalEntities.professor.ProfessorRepository;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private FichaRepository fichaRepository;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DadosCadastroAluno dados, UriComponentsBuilder uriBuilder, @PathVariable Long id){
        var professor = professorRepository.getReferenceById(id);
        var aluno = new Aluno(dados, professor);
        var uri = uriBuilder.path("/aluno/{id}").buildAndExpand(aluno.getId()).toUri();

        alunoRepository.save(aluno);

        return ResponseEntity.created(uri).body(new DadosDetalhamentoAluno(aluno));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoAluno>> listarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var alunos = alunoRepository.findAllByAtivoTrue(paginacao);
        var page = alunos.map(DadosDetalhamentoAluno::new);

        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listar(@PathVariable Long id){
        var aluno = alunoRepository.getReferenceById(id);
        return ResponseEntity.status(200).body(new DadosDetalhamentoAluno(aluno));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity editar(@RequestBody DadosAtualizacaoAluno dados , @PathVariable Long id){
        var aluno = alunoRepository.getReferenceById(id);
        aluno.atualizar(dados);
        return ResponseEntity.status(200).body(new DadosDetalhamentoAluno(aluno));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var aluno = alunoRepository.getReferenceById(id);
        aluno.deletar();

        return ResponseEntity.status(200).body(new DadosDetalhamentoAluno(aluno));
    }

    @GetMapping("/fichas/{id}")
    public ResponseEntity<Page<DadosDetalhamentoFicha>> listarFichas(@PathVariable Long id, @PageableDefault(size = 10) Pageable paginacao){
        var page = fichaRepository.findAllByAluno_id(paginacao, id).map(DadosDetalhamentoFicha::new);

        return ResponseEntity.ok().body(page);
    }

}
