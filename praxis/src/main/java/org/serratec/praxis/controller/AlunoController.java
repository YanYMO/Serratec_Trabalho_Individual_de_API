package org.serratec.praxis.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.serratec.praxis.domain.Aluno;
import org.serratec.praxis.dto.AlunoRequestDTO;
import org.serratec.praxis.dto.AlunoResponseDTO;
import org.serratec.praxis.dto.AlunoUpdateDTO;
import org.serratec.praxis.exception.ResourceNotFoundException;
import org.serratec.praxis.repository.AlunoRepository;
import org.serratec.praxis.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    @Operation(summary = "Lista todos os Alunos", description = "A resposta lista os Alunos cadastrados.")
    public ResponseEntity<List<AlunoResponseDTO>> listar() {

        return ResponseEntity.ok(alunoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca Aluno por ID", description = "A resposta é o Aluno referente ao ID passado.")
    public ResponseEntity<AlunoResponseDTO> buscarPorId(@PathVariable Long id) {
        AlunoResponseDTO alunoDTO = alunoService.findById(id);

        return ResponseEntity.ok(alunoDTO);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo Aluno", description = "A resposta é uma cópia dos dados que foram cadastrados.")
    public ResponseEntity<AlunoResponseDTO> cadastrar(@Valid @RequestBody AlunoRequestDTO aluno) {

        AlunoResponseDTO alunoDTO = alunoService.cadastrar(aluno);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(alunoDTO.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um Aluno", description = "A resposta é uma confirmação 200 OK")
    public ResponseEntity<AlunoResponseDTO> atualizar(@Valid @PathVariable Long id, @RequestBody AlunoRequestDTO alunoDTO) {
        alunoService.atualizar(id, alunoDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um Aluno", description = "A resposta é uma confirmação 200 OK")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        alunoService.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }

}