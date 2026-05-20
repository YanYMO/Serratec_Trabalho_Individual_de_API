package org.serratec.praxis.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.serratec.praxis.domain.Curso;
import org.serratec.praxis.domain.Professor;
import org.serratec.praxis.dto.CursoRequestDTO;
import org.serratec.praxis.dto.CursoResponseDTO;
import org.serratec.praxis.dto.ProfessorRequestDTO;
import org.serratec.praxis.dto.ProfessorResponseDTO;
import org.serratec.praxis.exception.CpfException;
import org.serratec.praxis.exception.EmailException;
import org.serratec.praxis.exception.ResourceNotFoundException;
import org.serratec.praxis.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    public List<CursoResponseDTO> findAll() {
        List<Curso> cursos = cursoRepository.findAll();

        if (cursos.isEmpty()) {
            throw new ResourceNotFoundException("Não existem Cursos cadastrados.");
        }

        List<CursoResponseDTO> cursoDTO = new ArrayList<CursoResponseDTO>();

        for (Curso curso : cursos) {
            cursoDTO.add(new CursoResponseDTO(curso));
        }
        return cursoDTO;
    }

    public CursoResponseDTO findById (Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos um Curso com esse identificador."));

        CursoResponseDTO cursoDTO = new CursoResponseDTO(curso);

        return cursoDTO;
    }

    @Transactional
    public CursoResponseDTO cadastrar(@Valid CursoRequestDTO cursoDTO) {

        Curso curso = new Curso();
        curso.setNome(cursoDTO.getNome());
        curso.setDescricao(cursoDTO.getDescricao());
        curso.setDataInicio(cursoDTO.getDataInicio());
        curso.setDuracaoEmHoras(cursoDTO.getDuracaoEmHoras());
        curso.setTipo(cursoDTO.getTipo());
        curso.setProfessores(new ArrayList<>());
        curso.setMatriculas(new ArrayList<>());

        cursoRepository.save(curso);

        return new CursoResponseDTO(curso);
    }

    @Transactional
    public Curso atualizar(@Valid Long id, CursoRequestDTO cursoDTO) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos um Curso com esse identificador."));

        curso.setNome(cursoDTO.getNome());
        curso.setDescricao(cursoDTO.getDescricao());
        curso.setDataInicio(cursoDTO.getDataInicio());
        curso.setDuracaoEmHoras(cursoDTO.getDuracaoEmHoras());
        curso.setTipo(cursoDTO.getTipo());

        return cursoRepository.save(curso);
    }

    @Transactional
    public void deletarPorId(Long id) {

        if (!cursoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Não encontramos um Curso com esse identificador.");
        }
        cursoRepository.deleteById(id);
    }
}
