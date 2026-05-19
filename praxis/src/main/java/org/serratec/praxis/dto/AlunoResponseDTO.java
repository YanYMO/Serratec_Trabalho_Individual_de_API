package org.serratec.praxis.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.serratec.praxis.domain.Aluno;
import org.serratec.praxis.domain.PerfilSocial;

@JsonPropertyOrder({
        "id",
        "nome",
        "email",
        "perfilSocial"
})

public class AlunoResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private PerfilSocial perfilSocial;

    public AlunoResponseDTO() {
        super();
    }

    public AlunoResponseDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.email = aluno.getEmail();
        this.perfilSocial = aluno.getPerfilSocial();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PerfilSocial getPerfilSocial() {
        return perfilSocial;
    }

    public void setPerfilSocial(PerfilSocial perfilSocial) {
        this.perfilSocial = perfilSocial;
    }
}
