package org.serratec.praxis.dto;

import java.time.LocalDate;

public class AlunoRequestDTO {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private String senha;
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
