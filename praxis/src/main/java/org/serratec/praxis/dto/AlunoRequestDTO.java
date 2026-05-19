package org.serratec.praxis.dto;

import java.time.LocalDate;

public class AlunoRequestDTO {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private PerfilSocialRequestDTO perfilSocialRequestDTO;

    public AlunoRequestDTO(String nome, String cpf, LocalDate dataNascimento, String email, PerfilSocialRequestDTO perfilSocialRequestDTO) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
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

    public PerfilSocialRequestDTO getPerfilSocialRequestDTO() {
        return perfilSocialRequestDTO;
    }

    public void setPerfilSocialRequestDTO(PerfilSocialRequestDTO perfilSocialRequestDTO) {
        this.perfilSocialRequestDTO = perfilSocialRequestDTO;
    }
}
