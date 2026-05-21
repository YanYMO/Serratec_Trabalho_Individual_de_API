package org.serratec.praxis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.serratec.praxis.dto.request.ProfessorRequestDTO;
import org.serratec.praxis.dto.response.ProfessorResponseDTO;
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum registro encontrado")
    })
    public ResponseEntity<List<ProfessorResponseDTO>> listar() {

        return ResponseEntity.ok(professorService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca Professor por ID", description = "A resposta é o Professor referente ao ID passado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registro encontrado"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado")
    })
    public ResponseEntity<ProfessorResponseDTO> buscarPorId(@PathVariable Long id) {
        ProfessorResponseDTO professorDTO = professorService.findById(id);

        return ResponseEntity.ok(professorDTO);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo Professor", description = "A resposta é uma cópia dos dados que foram cadastrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Registro já existe")
    })
    public ResponseEntity<ProfessorResponseDTO> cadastrar(@Valid @RequestBody ProfessorRequestDTO professor) {

        ProfessorResponseDTO professorDTO = professorService.cadastrar(professor);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(professorDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(professorDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um Professor por ID", description = "A resposta é uma confirmação 200 OK e o corpo do objeto atualizado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado")
    })
    public ResponseEntity<ProfessorResponseDTO> atualizar(@Valid @PathVariable Long id, @RequestBody ProfessorRequestDTO professorDTO) {


        return ResponseEntity.ok().body(professorService.atualizar(id, professorDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um Professor por ID", description = "A resposta é uma confirmação 204 NO CONTENT")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado")
    })
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        professorService.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }
}
