package treinoDieta.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import treinoDieta.api.physicalEntities.aluno.AlunoRepository;
import treinoDieta.api.physicalEntities.aluno.DadosCadastroAluno;
import treinoDieta.api.physicalEntities.professor.DadosCadastroProfessor;
import treinoDieta.api.physicalEntities.professor.DadosDetalhamentoProfessor;
import treinoDieta.api.physicalEntities.professor.Professor;
import treinoDieta.api.physicalEntities.professor.ProfessorRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ProfessorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroProfessor> dadosCadastroProfessorJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoProfessor> dadosDetalhamentoProfessorJson;
    @MockBean
    private ProfessorRepository professorRepository;

    @MockBean
    private AlunoRepository alunoRepository;

    @Test
    @DisplayName("Deveria devolver código 400 quando as informações estão inválidas")
    @WithMockUser(username = "admin", authorities = { "ROLE_ADMIN"})
    public void cadastrarProfessor_cenario1() throws Exception {
        var response = mvc.perform(post("/professor")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código 200 quando as informações estão corretas")
    @WithMockUser(username = "admin", authorities = { "ROLE_ADMIN"})
    void cadastrarProfessor_cenario2() throws Exception {

        var dadosDetalhamento = new DadosDetalhamentoProfessor(
                "gabriel", "22", "185", "gg@gmnail.com", 0L
        );

        var dadosCadastro = new DadosCadastroProfessor(
                "gabriel", "22", "185", "gg@gmnail.com"
        );

        when(professorRepository.save(any())).thenReturn(new Professor(dadosCadastro));

        var response = mvc.perform(MockMvcRequestBuilders.post("/professor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosDetalhamentoProfessorJson.write(
                                new DadosDetalhamentoProfessor("gabriel", "22", "185", "gg@gmnail.com", 0L)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosDetalhamentoProfessorJson.write(
                dadosDetalhamento
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

}