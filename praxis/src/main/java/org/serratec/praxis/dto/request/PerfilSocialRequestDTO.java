package org.serratec.praxis.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.serratec.praxis.enums.Genero;
import org.serratec.praxis.enums.NivelEscolaridade;
import org.serratec.praxis.enums.RendaFamiliar;

public class PerfilSocialRequestDTO {

    @NotNull(message = "O campo precisa ser preenchido")
    @Schema(description = "Genero do Aluno", example = "MASCULINO, FEMININO, OUTROS")
    private Genero genero;

    @NotNull(message = "O campo precisa ser preenchido")
    @Schema(description = "Nível de escolaridade do Aluno", example = "1 - Fundamental Imcompleto, 2 - Fundamental Completo, 3 - Médio Imcompleto, 4 - Medio Completo, 5 - Superior Imcompleto, 6 - Superior Completo")
    private NivelEscolaridade nivelEscolaridade;

    @NotNull(message = "O campo precisa ser preenchido")
    @Schema(description = "Renda familiar do Aluno", example = "1 - Até 1.580 reais, 2 - Entre 1.581 e 2.525, 3 - Entre 2.526 e 10.885, 4 - Entre 10.886 e 25.000, 5 - Acima de 25.000")
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
