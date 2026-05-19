package org.serratec.praxis.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.serratec.praxis.domain.PerfilSocial;
import org.serratec.praxis.domain.Professor;
import org.serratec.praxis.exception.ResourceNotFoundException;
import org.serratec.praxis.repository.PerfilSocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/perfil-social")
public class PerfilSocialController {

    @Autowired
    PerfilSocialRepository perfilSocialRepository;

    @GetMapping
    public ResponseEntity<List<PerfilSocial>> listar() {
        List<PerfilSocial> perfissociais = perfilSocialRepository.findAll();

        if (perfissociais.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(perfissociais);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca Aluno por ID", description = "A resposta é o Curso referente ao ID passado.")
    public ResponseEntity<PerfilSocial> buscarPorId(@PathVariable Long id) {
        PerfilSocial perfilSocial = perfilSocialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos um Curso com esse identificador."));

        return ResponseEntity.ok(perfilSocial);
    }

    @PostMapping
    public ResponseEntity<PerfilSocial> cadastrar(@Valid @RequestBody PerfilSocial perfilSocial) {
        perfilSocialRepository.save(perfilSocial);
        return ResponseEntity.status(HttpStatus.CREATED).body(perfilSocial);
    }

    /*@PutMapping("/perfis-sociais")
    public ResponseEntity<PerfilSocial> atualizar(@Valid @RequestBody PerfilSocial perfilSocial, @PathVariable Long id) {
        PerfilSocial ps = perfilSocialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos um Curso com esse identificador."));

        return ps;
    }*/

    @DeleteMapping("/{îd}")
    public ResponseEntity<Professor> deletar(@PathVariable Long id) {
        Optional professor = perfilSocialRepository.findById(id);

        if (professor.isEmpty()) {
            throw new ResourceNotFoundException("Não encontramos um Aluno com esse identificador.");
        }
        perfilSocialRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
