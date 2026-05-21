package org.serratec.praxis.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.serratec.praxis.domain.Aluno;
import org.serratec.praxis.domain.Curso;
import org.serratec.praxis.domain.Professor;
import org.serratec.praxis.dto.request.ProfessorRequestDTO;
import org.serratec.praxis.dto.response.ProfessorResponseDTO;
import org.serratec.praxis.exception.DuplicateEntryException;
import org.serratec.praxis.exception.ResourceNotFoundException;
import org.serratec.praxis.repository.AlunoRepository;
import org.serratec.praxis.repository.CursoRepository;
import org.serratec.praxis.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    CursoRepository cursoRepository;

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

        Professor pEmail = professorRepository.findByEmail(professorDTO.getEmail());
        Professor pCpf = professorRepository.findByCpf(professorDTO.getCpf());
        Aluno aEmail = alunoRepository.findByEmail(professorDTO.getEmail());
        Aluno aCpf = alunoRepository.findByCpf(professorDTO.getCpf());

        if (aEmail != null || pEmail != null) {
            throw new DuplicateEntryException("Email já cadastrado");
        }
        if (aCpf != null || pCpf != null) {
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
    public ProfessorResponseDTO atualizar(@Valid Long id, ProfessorRequestDTO professorDTO) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos um Professor com esse identificador."));

        Professor pEmail = professorRepository.findByEmail(professorDTO.getEmail());
        Professor pCpf = professorRepository.findByCpf(professorDTO.getCpf());
        Aluno aEmail = alunoRepository.findByEmail(professorDTO.getEmail());
        Aluno aCpf = alunoRepository.findByCpf(professorDTO.getCpf());

        if (pEmail != null && !pEmail.getId().equals(id) || aEmail != null && !aEmail.getId().equals(id)) {
            throw new DuplicateEntryException("Email já cadastrado");
        }
        if (pCpf != null && !pCpf.getId().equals(id) || aCpf != null && !aCpf.getId().equals(id)) {
            throw new DuplicateEntryException("CPF já cadastrado");
        }

        professor.setNome(professorDTO.getNome());
        professor.setCpf(professorDTO.getCpf());
        professor.setEmail(professorDTO.getEmail());
        professor.setSenha(professorDTO.getSenha());
        professor.setDataNascimento(professorDTO.getDataNascimento());

        professorRepository.save(professor);

        return new ProfessorResponseDTO(professor);
    }

    @Transactional
    public void deletarPorId(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos um Professor com esse identificador."));

        for (Curso curso : professor.getCursos()) {
            curso.getProfessores().remove(professor);
            cursoRepository.save(curso);
        }
        professorRepository.deleteById(id);
    }
}
