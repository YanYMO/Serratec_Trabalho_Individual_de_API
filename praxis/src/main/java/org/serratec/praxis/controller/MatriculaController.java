package org.serratec.praxis.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.serratec.praxis.domain.Matricula;
import org.serratec.praxis.exception.ResourceNotFoundException;
import org.serratec.praxis.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    MatriculaRepository matriculaRepository;

    @GetMapping
    public ResponseEntity<List<Matricula>> listar() {
        List<Matricula> matriculas = matriculaRepository.findAll();

        if (matriculas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca Aluno por ID", description = "A resposta é o Curso referente ao ID passado.")
    public ResponseEntity<Matricula> buscarPorId(@PathVariable Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos um Curso com esse identificador."));

        return ResponseEntity.ok(matricula);
    }

    @PostMapping
    public ResponseEntity<Matricula> cadastrar(@Valid @RequestBody Matricula matricula) {
        matriculaRepository.save(matricula);
        return ResponseEntity.status(HttpStatus.CREATED).body(matricula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Matricula> deletar(@PathVariable Long id) {
        Optional matricula = matriculaRepository.findById(id);

        if (matricula.isEmpty()) {
            throw new ResourceNotFoundException("Não encontramos um Aluno com esse identificador.");
        }
        matriculaRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
