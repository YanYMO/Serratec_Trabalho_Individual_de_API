package org.serratec.praxis.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.serratec.praxis.enums.StatusMatricula;

import java.time.LocalDate;

@Entity
@Table(name = "matricula")
public class Matricula {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo precisa ser preenchido")
    @Max(99999)
    @Column(name = "codigo", nullable = false, length = 99999, unique = true)
    private Integer codigo;

    @CreationTimestamp
    @Column(name = "data_de_matricula", updatable = false)
    private LocalDate dataMatricula;

    @NotNull(message = "O campo precisa ser preenchido")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusMatricula status;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    public Matricula(Long id, Integer codigo, LocalDate dataMatricula, StatusMatricula status, Aluno aluno, Curso curso) {
        this.id = id;
        this.codigo = codigo;
        this.dataMatricula = dataMatricula;
        this.status = status;
        this.aluno = aluno;
        this.curso = curso;
    }

    public Matricula() {
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

    public void setDataMatricula(LocalDate dataMAtricula) {
        this.dataMatricula = dataMatricula;
    }

    public StatusMatricula getStatus() {
        return status;
    }

    public void setStatus(StatusMatricula status) {
        this.status = status;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
