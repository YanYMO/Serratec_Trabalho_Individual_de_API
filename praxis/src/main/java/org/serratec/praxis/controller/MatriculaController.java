package org.serratec.praxis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.serratec.praxis.dto.request.MatriculaRequestDTO;
import org.serratec.praxis.dto.request.MatriculaUpdateDTO;
import org.serratec.praxis.dto.response.MatriculaResponseDTO;
import org.serratec.praxis.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    MatriculaService matriculaService;

    @GetMapping
    @Operation(summary = "Lista todas as Matriculas", description = "A resposta lista as Matriculas cadastradas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum registro encontrado")
    })
    public ResponseEntity<List<MatriculaResponseDTO>> listar() {

        return ResponseEntity.ok(matriculaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca Matricula por ID", description = "A resposta é a Matricula referente ao ID passado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registro encontrado"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado")
    })
    public ResponseEntity<MatriculaResponseDTO> buscarPorId(@PathVariable Long id) {
        MatriculaResponseDTO matriculaDTO = matriculaService.findById(id);

        return ResponseEntity.ok(matriculaDTO);
    }

    @PostMapping
    @Operation(summary = "Cadastra uma nova Matricula", description = "A resposta é uma confirmação 200 OK e o corpo do objeto atualizado.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Registro já existe")
    })
    public ResponseEntity<MatriculaResponseDTO> cadastrarMatricula(@Valid @RequestBody MatriculaRequestDTO matricula) {

        MatriculaResponseDTO matriculaDTO = matriculaService.cadastrarMatricula(matricula);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(matriculaDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(matriculaDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza a Matricula por ID", description = "A resposta é uma confirmação 200 OK e o corpo do objeto atualizado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado")
    })
    public ResponseEntity<MatriculaResponseDTO> atualizar(@Valid @PathVariable Long id, @RequestBody MatriculaUpdateDTO UpdateDTO) {

        return ResponseEntity.ok().body(matriculaService.atualizar(id, UpdateDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma Matricula por ID", description = "A resposta é uma confirmação 204 NO CONTENT")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado")
    })
    public ResponseEntity<Object> deletarPorId(@PathVariable Long id) {
        matriculaService.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }
}
