package org.serratec.praxis.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.serratec.praxis.domain.Aluno;
import org.serratec.praxis.domain.PerfilSocial;
import org.serratec.praxis.enums.Genero;
import org.serratec.praxis.enums.NivelEscolaridade;
import org.serratec.praxis.enums.RendaFamiliar;

@JsonPropertyOrder({
        "id",
        "genero",
        "nivelEscolaridade",
        "rendaFamiliar",
        "aluno"
})

public class PerfilSocialResponseDTO {

    private Long id;
    private Genero genero;
    private NivelEscolaridade nivelEscolaridade;
    private RendaFamiliar rendaFamiliar;
    private Aluno aluno;

    public PerfilSocialResponseDTO() {
        super();
    }

    public PerfilSocialResponseDTO(PerfilSocial perfilSocial) {
        this.id = perfilSocial.getId();
        this.genero = perfilSocial.getGenero();
        this.nivelEscolaridade = perfilSocial.getEscolaridade();
        this.rendaFamiliar = perfilSocial.getRendaFamiliar();
        this.aluno = perfilSocial.getAluno();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public NivelEscolaridade getNivelEscolaridade() {
        return nivelEscolaridade;
    }

    public void setNivelEscolaridade(NivelEscolaridade nivelEscolaridade) {
        this.nivelEscolaridade = nivelEscolaridade;
    }

    public RendaFamiliar getRendaFamiliar() {
        return rendaFamiliar;
    }

    public void setRendaFamiliar(RendaFamiliar rendaFamiliar) {
        this.rendaFamiliar = rendaFamiliar;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
