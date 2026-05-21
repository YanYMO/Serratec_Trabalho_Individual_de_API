package org.serratec.praxis.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.serratec.praxis.domain.Professor;
import org.serratec.praxis.dto.*;
import org.serratec.praxis.exception.DuplicateEntryException;
import org.serratec.praxis.exception.ResourceNotFoundException;
import org.serratec.praxis.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    public List<ProfessorResponseDTO> findAll() {
        List<Professor> professores = professorRepository.findAll();

        if (professores.isEmpty()) {
            throw new ResourceNotFoundException("Não existem Professores cadastrados.");
        }

        List<ProfessorResponseDTO> professoresDTO = new ArrayList<ProfessorResponseDTO>();

        for (Professor professor : professores) {
            professoresDTO.add(new ProfessorResponseDTO(professor));
        }
        return professoresDTO;
    }

    public ProfessorResponseDTO findById (Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos um Professor com esse identificador."));

        ProfessorResponseDTO professorDTO = new ProfessorResponseDTO(professor);

        return professorDTO;
    }

    @Transactional
    public ProfessorResponseDTO cadastrar(@Valid ProfessorRequestDTO professorDTO) {

        Professor a = professorRepository.findByEmail(professorDTO.getEmail());
        Professor b = professorRepository.findByCpf(professorDTO.getCpf());

        if (a != null) {
            throw new DuplicateEntryException("Email já cadastrado");
        }
        if (b != null) {
            throw new DuplicateEntryException("CPF já cadastrado");
        }

        Professor professor = new Professor();
        professor.setNome(professorDTO.getNome());
        professor.setCpf(professorDTO.getCpf());
        professor.setEmail(professorDTO.getEmail());
        professor.setSenha(professorDTO.getSenha());
        professor.setDataNascimento(professorDTO.getDataNascimento());

        professorRepository.save(professor);

        return new ProfessorResponseDTO(professor);
    }

    @Transactional
    public Professor atualizar(@Valid Long id, ProfessorRequestDTO professorDTO) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos um Professor com esse identificador."));

        Professor a = professorRepository.findByEmail(professorDTO.getEmail());
        Professor b = professorRepository.findByCpf(professorDTO.getCpf());

        if (a != null) {
            throw new DuplicateEntryException("Email já cadastrado");
        }
        if (b != null) {
            throw new DuplicateEntryException("CPF já cadastrado");
        }

        professor.setNome(professorDTO.getNome());
        professor.setCpf(professorDTO.getCpf());
        professor.setEmail(professorDTO.getEmail());
        professor.setSenha(professorDTO.getSenha());
        professor.setDataNascimento(professorDTO.getDataNascimento());

        return professorRepository.save(professor);
    }

    @Transactional
    public void deletarPorId(Long id) {

        if (!professorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Não encontramos um Aluno com esse identificador.");
        }
        professorRepository.deleteById(id);
    }
}
