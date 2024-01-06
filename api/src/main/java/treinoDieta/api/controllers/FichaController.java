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
import treinoDieta.api.nonPhysicalEntities.ficha.Ficha;
import treinoDieta.api.nonPhysicalEntities.ficha.FichaRepository;
import treinoDieta.api.physicalEntities.aluno.Aluno;
import treinoDieta.api.physicalEntities.aluno.AlunoRepository;
import treinoDieta.api.physicalEntities.professor.DadosDetalhamentoProfessor;

@RestController
@RequestMapping("/ficha")
public class FichaController {

    @Autowired
    private FichaRepository fichaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroFicha dados, @PathVariable Long id, UriComponentsBuilder uriBuilder){
        var aluno = alunoRepository.getReferenceById(id);
        var ficha = fichaRepository.save(new Ficha(dados, aluno));
        var uri = uriBuilder.path("/ficha/{id}").buildAndExpand(ficha.getId()).toUri();

        return  ResponseEntity.created(uri).body(new DadosDetalhamentoFicha(ficha));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoFicha>> todasFichas(@PageableDefault(size = 10) Pageable paginacao){
        var page = fichaRepository.findAll(paginacao).map(DadosDetalhamentoFicha::new);

        return ResponseEntity.ok().body(page);
    }

}
