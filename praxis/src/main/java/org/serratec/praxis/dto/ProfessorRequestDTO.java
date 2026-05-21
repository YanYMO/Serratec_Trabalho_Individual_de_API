package org.serratec.praxis.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public class ProfessorRequestDTO {

    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(max = 80)
    private String nome;

    @NotBlank(message = "O campo precisa ser preenchido")
    @CPF
    private String cpf;

    @NotNull(message = "O campo precisa ser preenchido")
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(max = 60)
    @Email
    private String email;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(min = 12, max = 30, message = "A senha precisa ter entre 12 e 30 caracteres")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{12,30}$", message = "A senha deve conter letras e números")
    private String senha;

    public ProfessorRequestDTO(String nome, String cpf, LocalDate dataNascimento, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
    }

    public ProfessorRequestDTO() {
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
