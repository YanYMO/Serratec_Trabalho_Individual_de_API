package org.serratec.praxis.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.serratec.praxis.domain.Aluno;
import org.serratec.praxis.domain.PerfilSocial;
import org.serratec.praxis.dto.AlunoRequestDTO;
import org.serratec.praxis.dto.AlunoResponseDTO;
import org.serratec.praxis.dto.AlunoUpdateDTO;
import org.serratec.praxis.exception.CpfException;
import org.serratec.praxis.exception.EmailException;
import org.serratec.praxis.exception.ResourceNotFoundException;
import org.serratec.praxis.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

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

    public AlunoResponseDTO findById (Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos um Aluno com esse identificador."));

        AlunoResponseDTO alunoDTO = new AlunoResponseDTO(aluno);

        return alunoDTO;
    }

    @Transactional
    public AlunoResponseDTO cadastrar(@Valid AlunoRequestDTO alunoDTO) {

        Aluno a = alunoRepository.findByEmail(alunoDTO.getEmail());
        Aluno b = alunoRepository.findByCpf(alunoDTO.getCpf());

        if (a != null) {
            throw new EmailException("Email já cadastrado");
        }
        if (b != null) {
            throw new CpfException("CPF já cadastrado");
        }

        PerfilSocial perfil = new PerfilSocial();
        perfil.setGenero(alunoDTO.getPerfilSocialRequestDTO().getGenero());
        perfil.setEscolaridade(alunoDTO.getPerfilSocialRequestDTO().getNivelEscolaridade());
        perfil.setRendaFamiliar(alunoDTO.getPerfilSocialRequestDTO().getRendaFamiliar());

        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.getNome());
        aluno.setCpf(alunoDTO.getCpf());
        aluno.setEmail(alunoDTO.getEmail());
        aluno.setSenha(alunoDTO.getSenha());
        aluno.setDataNascimento(alunoDTO.getDataNascimento());
        aluno.setPerfilSocial(perfil);

        alunoRepository.save(aluno);

        return new AlunoResponseDTO(aluno);
    }

    @Transactional
    public Aluno atualizar(@Valid Long id, AlunoRequestDTO alunoDTO) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontramos um Aluno com esse identificador."));

        Aluno a = alunoRepository.findByEmail(alunoDTO.getEmail());
        Aluno b = alunoRepository.findByCpf(alunoDTO.getCpf());

        if (a != null) {
            throw new EmailException("Email já cadastrado");
        }
        if (b != null) {
            throw new CpfException("CPF já cadastrado");
        }

        PerfilSocial perfil = new PerfilSocial();
        perfil.setGenero(alunoDTO.getPerfilSocialRequestDTO().getGenero());
        perfil.setEscolaridade(alunoDTO.getPerfilSocialRequestDTO().getNivelEscolaridade());
        perfil.setRendaFamiliar(alunoDTO.getPerfilSocialRequestDTO().getRendaFamiliar());

        aluno.setNome(alunoDTO.getNome());
        aluno.setCpf(alunoDTO.getCpf());
        aluno.setEmail(alunoDTO.getEmail());
        aluno.setSenha(alunoDTO.getSenha());
        aluno.setDataNascimento(alunoDTO.getDataNascimento());
        aluno.setPerfilSocial(perfil);

        alunoRepository.save(aluno);

        return alunoRepository.save(aluno);
    }

    @Transactional
    public void deletarPorId(Long id) {

        if (!alunoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Não encontramos um Aluno com esse identificador.");
        }
        alunoRepository.deleteById(id);
    }
}