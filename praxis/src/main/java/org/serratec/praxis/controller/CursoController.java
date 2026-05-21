package org.serratec.praxis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.serratec.praxis.dto.request.CursoRequestDTO;
import org.serratec.praxis.dto.response.CursoResponseDTO;
import org.serratec.praxis.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    CursoService cursoService;

    @GetMapping
    @Operation(summary = "Lista todos os Cursos", description = "A resposta lista os Cursos cadastrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum registro encontrado")
    })
    public ResponseEntity<List<CursoResponseDTO>> listar() {

        return ResponseEntity.ok(cursoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca Curso por ID", description = "A resposta é o Curso referente ao ID passado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registro encontrado"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado")
    })
    public ResponseEntity<CursoResponseDTO> buscarPorId(@PathVariable Long id) {
        CursoResponseDTO cursoDTO = cursoService.findById(id);

        return ResponseEntity.ok(cursoDTO);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo Curso", description = "A resposta é uma cópia dos dados que foram cadastrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Registro já existe")
    })
    public ResponseEntity<CursoResponseDTO> cadastrarCurso(@Valid @RequestBody CursoRequestDTO curso) {

        CursoResponseDTO cursoDTO = cursoService.cadastrarCurso(curso);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cursoDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(cursoDTO);
    }

    @PostMapping("/curso/{cursoId}/professor/{professorId}")
    @Operation(summary = "Cadastra um Professor no Curso utilizando o ID de ambos", description = "A resposta é uma cópia dos dados que foram cadastrados.")
    public ResponseEntity<CursoResponseDTO> cadastrarProfessorCurso(@Valid @PathVariable Long cursoId, @PathVariable Long professorId) {

        CursoResponseDTO cursoDTO = cursoService.cadastrarProfessorCurso(cursoId, professorId);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cursoDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(cursoDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um Curso por ID", description = "A resposta é uma confirmação 200 OK e o corpo do objeto atualizado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado")
    })
    public ResponseEntity<CursoResponseDTO> atualizar(@Valid @PathVariable Long id, @RequestBody CursoRequestDTO cursoDTO) {

        return ResponseEntity.ok().body(cursoService.atualizar(id, cursoDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um Curso por ID", description = "A resposta é uma confirmação 204 NO CONTENT")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado")
    })
    public ResponseEntity<Object> deletarPorId(@PathVariable Long id) {
        cursoService.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }
}
