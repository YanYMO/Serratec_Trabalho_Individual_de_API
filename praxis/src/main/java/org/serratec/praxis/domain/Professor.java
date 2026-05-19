package org.serratec.praxis.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "professor")
public class Professor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(max = 80)
    @Column(name = "nome_professor", nullable = false, length = 80)
    private String nome;

    @NotBlank(message = "O campo precisa ser preenchido")
    @CPF
    @Column(name = "cpf", nullable = false)
    private String cpf;

    @NotNull(message = "O campo precisa ser preenchido")
    @Past
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(max = 60)
    @Email
    @Column(name = "email", nullable = false, length = 60)
    private String email;

    @ManyToMany(mappedBy = "professores")
    private List<Curso> cursos;

    public Professor(Long id, String nome, String cpf, LocalDate dataNascimento, String email, List<Curso> cursos) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.cursos = cursos;
    }

    public Professor() {
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
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
