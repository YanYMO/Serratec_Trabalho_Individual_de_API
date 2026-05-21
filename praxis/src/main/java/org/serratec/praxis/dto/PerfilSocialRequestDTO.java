package org.serratec.praxis.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import org.serratec.praxis.enums.Genero;
import org.serratec.praxis.enums.NivelEscolaridade;
import org.serratec.praxis.enums.RendaFamiliar;

public class PerfilSocialRequestDTO {

    @NotNull(message = "O campo precisa ser preenchido")
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @NotNull(message = "O campo precisa ser preenchido")
    @Enumerated(EnumType.ORDINAL)
    private NivelEscolaridade nivelEscolaridade;

    @NotNull(message = "O campo precisa ser preenchido")
    @Enumerated(EnumType.ORDINAL)
    private RendaFamiliar rendaFamiliar;

    public PerfilSocialRequestDTO(Genero genero, NivelEscolaridade nivelEscolaridade, RendaFamiliar rendaFamiliar) {
        this.genero = genero;
        this.nivelEscolaridade = nivelEscolaridade;
        this.rendaFamiliar = rendaFamiliar;
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

}
