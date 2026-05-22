package org.serratec.praxis.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.serratec.praxis.domain.PerfilSocial;

@JsonPropertyOrder({
        "id",
        "genero",
        "escolaridade",
        "rendaFamiliar",
        "aluno"
})

public class PerfilSocialResponseDTO {

    private Long id;
    private String genero;
    private String escolaridade;
    private String rendaFamiliar;
    private String aluno;

    public PerfilSocialResponseDTO() {
        super();
    }

    public PerfilSocialResponseDTO(PerfilSocial perfilSocial) {
        this.id = perfilSocial.getId();
        this.genero = perfilSocial.getGenero().toString();
        this.escolaridade = perfilSocial.getEscolaridade().getNivel().toUpperCase();
        this.rendaFamiliar = perfilSocial.getRendaFamiliar().getRenda().toUpperCase();
        this.aluno = perfilSocial.getAluno().getNome().toUpperCase();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }
}
