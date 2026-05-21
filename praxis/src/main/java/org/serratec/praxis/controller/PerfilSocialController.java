package org.serratec.praxis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.serratec.praxis.dto.request.PerfilSocialRequestDTO;
import org.serratec.praxis.dto.response.AlunoResponseDTO;
import org.serratec.praxis.dto.response.PerfilSocialResponseDTO;
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum registro encontrado")
    })
    public ResponseEntity<List<PerfilSocialResponseDTO>> listar() {

        return ResponseEntity.ok(perfilSocialService.findAll());
    }

    @PutMapping("/aluno/{IdAluno}")
    @Operation(summary = "Atualiza o Perfil Social", description = "A resposta é uma confirmação 200 OK e o corpo do objeto atualizado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado")
    })
    public ResponseEntity<AlunoResponseDTO> atualizarPerfil(@Valid @PathVariable Long IdAluno, @RequestBody PerfilSocialRequestDTO perfilDTO) {

        return ResponseEntity.ok(perfilSocialService.atualizarPerfil(IdAluno, perfilDTO));
    }
}
