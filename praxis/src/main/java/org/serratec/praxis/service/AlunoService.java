package org.serratec.praxis.service;

import org.serratec.praxis.domain.Aluno;
import org.serratec.praxis.dto.AlunoResponseDTO;
import org.serratec.praxis.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<AlunoResponseDTO> findAll() {
        List<Aluno> alunos = alunoRepository.findAll();

        List<AlunoResponseDTO> alunosDTO = new ArrayList<AlunoResponseDTO>();

        for (Aluno aluno : alunos) {
            alunosDTO.add(new AlunoResponseDTO(aluno));
        }

        return alunosDTO;
    }
}

