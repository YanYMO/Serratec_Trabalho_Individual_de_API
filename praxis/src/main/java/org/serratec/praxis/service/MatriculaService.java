package org.serratec.praxis.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.serratec.praxis.domain.Aluno;
import org.serratec.praxis.domain.Curso;
import org.serratec.praxis.domain.Matricula;
import org.serratec.praxis.dto.request.MatriculaRequestDTO;
import org.serratec.praxis.dto.response.MatriculaResponseDTO;
import org.serratec.praxis.dto.request.MatriculaUpdateDTO;
import org.serratec.praxis.exception.ResourceNotFoundException;
import org.serratec.praxis.repository.AlunoRepository;
import org.serratec.praxis.repository.CursoRepository;
import org.serratec.praxis.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    MatriculaRepository matriculaRepository;
    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    CursoRepository cursoRepository;

    public List<MatriculaResponseDTO> findAll() {
        List<Matricula> matriculas = matriculaRepository.findAll();

        if (matriculas.isEmpty()) {
            throw new ResourceNotFoundException("Não existem Matriculas cadastradas.");
        }

        List<MatriculaResponseDTO> matriculaDTO = new ArrayList<MatriculaResponseDTO>();

        for (Matricula matricula : matriculas) {
            matriculaDTO.add(new MatriculaResponseDTO(matricula));
        }
        return matriculaDTO;
    }

    public MatriculaResponseDTO findById (Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos uma Matricula com esse identificador."));

        MatriculaResponseDTO matriculaDTO = new MatriculaResponseDTO(matricula);

        return matriculaDTO;
    }

    @Transactional
    public MatriculaResponseDTO cadastrarMatricula(@Valid MatriculaRequestDTO matriculaDTO) {
        Aluno aluno = alunoRepository.findById(matriculaDTO.getAlunoId())
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos um Aluno com esse identificador."));

        Curso curso = cursoRepository.findById(matriculaDTO.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos um Curso com esse identificador."));

        Matricula matricula = new Matricula();
        matricula.setCodigo(matriculaDTO.getCodigo());
        matricula.setStatus(matriculaDTO.getStatus());
        matricula.setAluno(aluno);
        matricula.setCurso(curso);

        matriculaRepository.save(matricula);

        return new MatriculaResponseDTO(matricula);
    }

    @Transactional
    public MatriculaResponseDTO atualizar(@Valid Long id, MatriculaUpdateDTO UpdateDTO) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos uma Matricula com esse identificador."));

        matricula.setStatus(UpdateDTO.getStatus());
        matriculaRepository.save(matricula);

        return new MatriculaResponseDTO(matricula);
    }

    @Transactional
    public void deletarPorId(Long id) {

        if (!matriculaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Não encontramos uma Matricula com esse identificador.");
        }
        matriculaRepository.deleteById(id);
    }
}
