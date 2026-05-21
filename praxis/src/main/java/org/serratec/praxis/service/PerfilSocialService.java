package org.serratec.praxis.service;

import jakarta.validation.Valid;
import org.serratec.praxis.domain.Aluno;
import org.serratec.praxis.domain.PerfilSocial;
import org.serratec.praxis.dto.AlunoResponseDTO;
import org.serratec.praxis.dto.PerfilSocialRequestDTO;
import org.serratec.praxis.dto.PerfilSocialResponseDTO;
import org.serratec.praxis.exception.ResourceNotFoundException;
import org.serratec.praxis.repository.AlunoRepository;
import org.serratec.praxis.repository.PerfilSocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerfilSocialService {

    @Autowired
    PerfilSocialRepository perfilSocialRepository;
    @Autowired
    AlunoRepository alunoRepository;

    public List<PerfilSocialResponseDTO> findAll() {
        List<PerfilSocial> perfis = perfilSocialRepository.findAll();

        if (perfis.isEmpty()) {
            throw new ResourceNotFoundException("Não existem Perfis Sociais cadastrados.");
        }
        List<PerfilSocialResponseDTO> perfisDTO = new ArrayList<PerfilSocialResponseDTO>();

        for (PerfilSocial perfil : perfis) {
            perfisDTO.add(new PerfilSocialResponseDTO(perfil));
        }
        return perfisDTO;
    }

    public AlunoResponseDTO atualizarPerfil(@Valid Long idAluno, PerfilSocialRequestDTO perfilDTO) {
        Aluno aluno = alunoRepository.findById(idAluno)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos um Aluno com esse identificador."));

        aluno.getPerfilSocial().setGenero(perfilDTO.getGenero());
        aluno.getPerfilSocial().setEscolaridade(perfilDTO.getNivelEscolaridade());
        aluno.getPerfilSocial().setRendaFamiliar(perfilDTO.getRendaFamiliar());

        aluno = alunoRepository.save(aluno);

        return new AlunoResponseDTO(aluno);
    }
}
