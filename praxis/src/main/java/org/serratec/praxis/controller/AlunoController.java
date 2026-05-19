package org.serratec.praxis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.serratec.praxis.domain.Aluno;
import org.serratec.praxis.dto.AlunoResponseDTO;
import org.serratec.praxis.exception.ResourceNotFoundException;
import org.serratec.praxis.repository.AlunoRepository;
import org.serratec.praxis.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    @Operation(summary = "Lista todos os Alunos", description = "A resposta lista os alunos cadastrados.")
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
    public ResponseEntity<Aluno> cadastrar(@Valid @RequestBody Aluno aluno) {
        alunoRepository.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @DeleteMapping("/{îd}")
    public ResponseEntity<Aluno> deletar(@PathVariable Long id) {
        Optional aluno = alunoRepository.findById(id);

        if (aluno.isEmpty()) {
            throw new ResourceNotFoundException("Não encontramos um Aluno com esse identificador.");
        }
        alunoRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}

