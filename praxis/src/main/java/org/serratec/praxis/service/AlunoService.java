package org.serratec.praxis.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.serratec.praxis.domain.Aluno;
import org.serratec.praxis.dto.AlunoResponseDTO;
import org.serratec.praxis.dto.AlunoUpdateDTO;
import org.serratec.praxis.exception.CpfException;
import org.serratec.praxis.exception.EmailException;
import org.serratec.praxis.exception.ResourceNotFoundException;
import org.serratec.praxis.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<AlunoResponseDTO> findAll() {
        List<Aluno> alunos = alunoRepository.findAll();

        if (alunos.isEmpty()) {
            throw new ResourceNotFoundException("Não existem Alunos cadastrados.");
        }

        List<AlunoResponseDTO> alunosDTO = new ArrayList<AlunoResponseDTO>();

        for (Aluno aluno : alunos) {
            alunosDTO.add(new AlunoResponseDTO(aluno));
        }
        return alunosDTO;
    }

    @GetMapping("/{id}")
    public AlunoResponseDTO findById (Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos um Aluno com esse identificador."));

        AlunoResponseDTO alunoDTO = new AlunoResponseDTO(aluno);
        return alunoDTO;
    }

    @Transactional
    @PostMapping
    public Aluno cadastrar(@Valid Aluno aluno) {

        Aluno a = alunoRepository.findByEmail(aluno.getEmail());
        Aluno b = alunoRepository.findByCpf(aluno.getCpf());

        if (a != null) {
            throw new EmailException("Email já cadastrado");
        }
        if (b != null) {
            throw new CpfException("CPF já cadastrado");
        }

        return alunoRepository.save(aluno);
    }

    @Transactional
    @PutMapping("/{id}")
    public Aluno atualizar(@Valid Long id, AlunoUpdateDTO alunoDTO) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos um Aluno com esse identificador."));

        aluno.setNome(alunoDTO.getNome());
        aluno.setEmail(alunoDTO.getEmail());
        aluno.setDataNascimento(alunoDTO.getDataNascimento());

        return alunoRepository.save(aluno);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deletarPorId(Long id) {

        if (!alunoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Não encontramos um Aluno com esse identificador.");
        }
        alunoRepository.deleteById(id);
    }
}

