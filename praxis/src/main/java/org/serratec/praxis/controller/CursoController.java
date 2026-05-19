package org.serratec.praxis.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.serratec.praxis.domain.Aluno;
import org.serratec.praxis.domain.Curso;
import org.serratec.praxis.exception.ResourceNotFoundException;
import org.serratec.praxis.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    CursoRepository cursoRepository;

    public ResponseEntity<List<Curso>> listar() {
        List<Curso> cursos = cursoRepository.findAll();

        if (cursos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca Aluno por ID", description = "A resposta é o Curso referente ao ID passado.")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos um Curso com esse identificador."));

        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<Curso> cadastrar(@Valid @RequestBody Curso curso) {
        cursoRepository.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    @DeleteMapping("/{îd}")
    public ResponseEntity<Curso> deletar(@PathVariable Long id) {
        Optional curso = cursoRepository.findById(id);

        if (curso.isEmpty()) {
            throw new ResourceNotFoundException("Não encontramos um Aluno com esse identificador.");
        }
        cursoRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
