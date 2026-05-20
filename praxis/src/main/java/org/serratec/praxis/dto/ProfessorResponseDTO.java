package org.serratec.praxis.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.serratec.praxis.domain.Curso;
import org.serratec.praxis.domain.Professor;

import java.util.List;

@JsonPropertyOrder({
        "id",
        "nome",
        "cpf",
        "email",
        "cursos"
})

public class ProfessorResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private List<Curso> cursos;

    public ProfessorResponseDTO() {
        super();
    }

    public ProfessorResponseDTO(Professor professor) {
        this.id = professor.getId();
        this.nome = professor.getNome();
        this.cpf = professor.getCpf();
        this.email = professor.getEmail();
        this.cursos = professor.getCursos();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
