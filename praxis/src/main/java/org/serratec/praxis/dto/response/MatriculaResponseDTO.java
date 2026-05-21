package org.serratec.praxis.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.serratec.praxis.domain.Matricula;
import org.serratec.praxis.enums.StatusMatricula;

import java.time.LocalDate;

@JsonPropertyOrder({
        "id",
        "codigo",
        "dataMatricula",
        "status",
        "aluno",
        "curso"
})

public class MatriculaResponseDTO {

    private Long id;
    private Integer codigo;
    private LocalDate dataMatricula;
    private StatusMatricula status;
    private String aluno;
    private String curso;

    public MatriculaResponseDTO(Matricula matricula) {
        this.id = matricula.getId();
        this.codigo = matricula.getCodigo();
        this.dataMatricula = matricula.getDataMatricula();
        this.status = matricula.getStatus();
        this.aluno = matricula.getAluno().getNome();
        this.curso = matricula.getCurso().getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public StatusMatricula getStatus() {
        return status;
    }

    public void setStatus(StatusMatricula status) {
        this.status = status;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
