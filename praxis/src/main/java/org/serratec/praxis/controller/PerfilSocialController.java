package org.serratec.praxis.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.serratec.praxis.domain.PerfilSocial;
import org.serratec.praxis.domain.Professor;
import org.serratec.praxis.dto.AlunoResponseDTO;
import org.serratec.praxis.dto.PerfilSocialRequestDTO;
import org.serratec.praxis.dto.PerfilSocialResponseDTO;
import org.serratec.praxis.exception.ResourceNotFoundException;
import org.serratec.praxis.repository.PerfilSocialRepository;
import org.serratec.praxis.service.PerfilSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/perfis-sociais")
public class PerfilSocialController {

    @Autowired
    PerfilSocialService perfilSocialService;

    @GetMapping
    @Operation(summary = "Lista todos os Perfis Sociais", description = "A resposta são os Perfis Sociais com uma mensão ao Aluno pertencente.")
    public ResponseEntity<List<PerfilSocialResponseDTO>> listar() {

        return ResponseEntity.ok(perfilSocialService.findAll());
    }

    @PutMapping("/aluno/{IdAluno}")
    @Operation(summary = "Atualiza o Perfil Social", description = "A resposta é o Aluno com os dados de perfil atualizados.")
    public ResponseEntity<AlunoResponseDTO> atualizarPerfil(@Valid @PathVariable Long IdAluno, @RequestBody PerfilSocialRequestDTO perfilDTO) {

        return ResponseEntity.ok(perfilSocialService.atualizarPerfil(IdAluno, perfilDTO));
    }

}
