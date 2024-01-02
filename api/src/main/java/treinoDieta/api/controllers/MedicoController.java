package treinoDieta.api.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import treinoDieta.api.professor.DadosCadastroProfessor;
import treinoDieta.api.professor.DadosListagemProfessor;
import treinoDieta.api.professor.Professor;
import treinoDieta.api.professor.ProfessorRepository;

@RestController
@RequestMapping("/professor")
public class MedicoController {
    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@Valid @RequestBody DadosCadastroProfessor dados, UriComponentsBuilder uriBuilder){
        var professor = professorRepository.save(new Professor(dados));
        var uri = uriBuilder.path("/professor/{id}").buildAndExpand(professor.getId()).toUri();

        return ResponseEntity.created(uri).body(professor);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemProfessor>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page = professorRepository.findAll(paginacao).map(DadosListagemProfessor::new);
        return ResponseEntity.ok().body(page);
    }

}
