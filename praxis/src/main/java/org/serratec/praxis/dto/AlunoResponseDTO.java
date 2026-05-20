package org.serratec.praxis.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.serratec.praxis.domain.Aluno;
import org.serratec.praxis.domain.PerfilSocial;
import org.serratec.praxis.enums.Genero;
import org.serratec.praxis.enums.NivelEscolaridade;
import org.serratec.praxis.enums.RendaFamiliar;

@JsonPropertyOrder({
        "id",
        "nome",
        "cpf",
        "email",
        "genero",
        "escolaridade",
        "rendaFamiliar"
})

public class AlunoResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String genero;
    private String escolaridade;
    private String rendaFamiliar;

    public AlunoResponseDTO() {
        super();
    }

    public AlunoResponseDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.cpf= aluno.getCpf();
        this.email = aluno.getEmail();
        this.genero = aluno.getPerfilSocial().getGenero().toString();
        this.escolaridade = aluno.getPerfilSocial().getEscolaridade().getNivel().toUpperCase();
        this.rendaFamiliar = aluno.getPerfilSocial().getRendaFamiliar().getRenda().toUpperCase();
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getRendaFamiliar() {
        return rendaFamiliar;
    }

    public void setRendaFamiliar(String rendaFamiliar) {
        this.rendaFamiliar = rendaFamiliar;
    }
}
