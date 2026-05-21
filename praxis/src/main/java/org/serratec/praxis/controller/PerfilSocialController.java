package org.serratec.praxis.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.serratec.praxis.dto.AlunoResponseDTO;
import org.serratec.praxis.dto.PerfilSocialRequestDTO;
import org.serratec.praxis.dto.PerfilSocialResponseDTO;
import org.serratec.praxis.service.PerfilSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Operation(summary = "Atualiza o Perfil Social", description = "A resposta é uma confirmação 200 OK e o corpo do objeto atualizado.")
    public ResponseEntity<AlunoResponseDTO> atualizarPerfil(@Valid @PathVariable Long IdAluno, @RequestBody PerfilSocialRequestDTO perfilDTO) {

        return ResponseEntity.ok(perfilSocialService.atualizarPerfil(IdAluno, perfilDTO));
    }
}
