package org.serratec.praxis.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.serratec.praxis.domain.Matricula;
import org.serratec.praxis.dto.*;
import org.serratec.praxis.exception.ResourceNotFoundException;
import org.serratec.praxis.service.CursoService;
import org.serratec.praxis.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    MatriculaService matriculaService;

    @GetMapping
    @Operation(summary = "Lista todas as Matriculas", description = "A resposta lista as Matriculas cadastradas.")
    public ResponseEntity<List<MatriculaResponseDTO>> listar() {

        return ResponseEntity.ok(matriculaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca Matricula por ID", description = "A resposta é a Matricula referente ao ID passado.")
    public ResponseEntity<MatriculaResponseDTO> buscarPorId(@PathVariable Long id) {
        MatriculaResponseDTO matriculaDTO = matriculaService.findById(id);

        return ResponseEntity.ok(matriculaDTO);
    }

    @PostMapping
    public ResponseEntity<MatriculaResponseDTO> cadastrarMatricula(@Valid @RequestBody MatriculaRequestDTO matricula) {

        MatriculaResponseDTO matriculaDTO = matriculaService.cadastrarMatricula(matricula);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(matriculaDTO.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza a Matricula por ID", description = "A resposta é uma confirmação 200 OK e o corpo do objeto atualizado.")
    public ResponseEntity<MatriculaResponseDTO> atualizar(@Valid @PathVariable Long id, @RequestBody MatriculaUpdateDTO UpdateDTO) {

        return ResponseEntity.ok().body(matriculaService.atualizar(id, UpdateDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um Curso por ID", description = "A resposta é uma confirmação 204 NO CONTENT")
    public ResponseEntity<Object> deletarPorId(@PathVariable Long id) {
        matriculaService.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }
}
