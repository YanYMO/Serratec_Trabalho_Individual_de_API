package org.serratec.praxis.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class AlunoUpdateDTO {
    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(max = 80)
    private String nome;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(max = 60)
    @Email
    private String email;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(min = 12, max = 30)
    @Column(name = "senha", nullable = false, length = 30)
    private String senha;

    @NotNull(message = "O campo precisa ser preenchido")
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;

    public AlunoUpdateDTO(String nome, String email, String senha, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }

    public AlunoUpdateDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
