package org.serratec.praxis.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.serratec.praxis.dto.AlunoResponseDTO;
import org.serratec.praxis.dto.ProfessorRequestDTO;
import org.serratec.praxis.dto.ProfessorResponseDTO;
import org.serratec.praxis.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @GetMapping
    @Operation(summary = "Lista todos os Professores", description = "A resposta lista os Professores cadastrados.")
    public ResponseEntity<List<ProfessorResponseDTO>> listar() {

        return ResponseEntity.ok(professorService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca Professor por ID", description = "A resposta é o Professor referente ao ID passado.")
    public ResponseEntity<ProfessorResponseDTO> buscarPorId(@PathVariable Long id) {
        ProfessorResponseDTO professorDTO = professorService.findById(id);

        return ResponseEntity.ok(professorDTO);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo Professor", description = "A resposta é uma cópia dos dados que foram cadastrados.")
    public ResponseEntity<ProfessorResponseDTO> cadastrar(@Valid @RequestBody ProfessorRequestDTO professor) {

        ProfessorResponseDTO professorDTO = professorService.cadastrar(professor);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(professorDTO.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um Professor por ID", description = "A resposta é uma confirmação 200 OK")
    public ResponseEntity<AlunoResponseDTO> atualizar(@Valid @PathVariable Long id, @RequestBody ProfessorRequestDTO professorDTO) {
        professorService.atualizar(id, professorDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um Professor por ID", description = "A resposta é uma confirmação 204 NO CONTENT")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        professorService.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }
}
