package org.serratec.praxis.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.serratec.praxis.domain.Curso;
import org.serratec.praxis.domain.Professor;
import org.serratec.praxis.exception.ResourceNotFoundException;
import org.serratec.praxis.repository.CursoRepository;
import org.serratec.praxis.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    ProfessorRepository professorRepository;

    public ResponseEntity<List<Professor>> listar() {
        List<Professor> professores = professorRepository.findAll();

        if (professores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(professores);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca Aluno por ID", description = "A resposta é o Curso referente ao ID passado.")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos um Curso com esse identificador."));

        return ResponseEntity.ok(professor);
    }

    @PostMapping
    public ResponseEntity<Professor> cadastrar(@Valid @RequestBody Professor professor) {
        professorRepository.save(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(professor);
    }

    @DeleteMapping("/{îd}")
    public ResponseEntity<Professor> deletar(@PathVariable Long id) {
        Optional professor = professorRepository.findById(id);

        if (professor.isEmpty()) {
            throw new ResourceNotFoundException("Não encontramos um Aluno com esse identificador.");
        }
        professorRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
