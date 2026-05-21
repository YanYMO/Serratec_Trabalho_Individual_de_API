package org.serratec.praxis.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public class AlunoRequestDTO {

    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(max = 80)
    @Schema(description = "Nome completo do aluno", example = "Yan Martins de Oliveira")
    private String nome;

    @NotBlank(message = "O campo precisa ser preenchido")
    @CPF
    @Schema(description = "CPF do Aluno", example = "00000000000")
    private String cpf;

    @NotNull(message = "O campo precisa ser preenchido")
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Schema(description = "Data de nascimento", example = "11/05/1999")
    private LocalDate dataNascimento;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(max = 60)
    @Email
    @Schema(description = "E-mail do Aluno", example = "exemplo@email.com")
    private String email;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(min = 12, max = 30, message = "A senha precisa ter entre 12 e 30 caracteres")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{12,30}$", message = "A senha deve conter letras e números")
    @Schema(description = "Senha do Aluno", example = "123456789lkjh")
    private String senha;

    @Schema(description = "Perfil Social do Aluno")
    private PerfilSocialRequestDTO perfilSocialRequestDTO;

    public AlunoRequestDTO(String nome, String cpf, LocalDate dataNascimento, String email, String senha, PerfilSocialRequestDTO perfilSocialRequestDTO) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
        this.perfilSocialRequestDTO = perfilSocialRequestDTO;
    }

    public AlunoRequestDTO() {
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

    public PerfilSocialRequestDTO getPerfilSocialRequestDTO() {
        return perfilSocialRequestDTO;
    }

    public void setPerfilSocialRequestDTO(PerfilSocialRequestDTO perfilSocialRequestDTO) {
        this.perfilSocialRequestDTO = perfilSocialRequestDTO;
    }

}
