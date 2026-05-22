package org.serratec.praxis.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.serratec.praxis.exception.EnumValidationException;

@Schema(description = "Genero do Aluno")
public enum Genero {
    @Schema(description = "Masculino")
    MASCULINO,
    @Schema(description = "Feminino")
    FEMININO,
    @Schema(description = "Outros")
    OUTROS;

    @JsonCreator
    public static Genero verifica(String value) throws EnumValidationException {
        if (value == null) {
            throw new EnumValidationException("Genero Inválido. Valores válidos: MASCULINO, FEMININO, OUTROS");
        }

        for (Genero g : values()) {
            if(value.equals(g.name())) {
                return g;
            }
        }
        throw new EnumValidationException(
                "Genero Inválido. Valores válidos: MASCULINO, FEMININO, OUTROS");
    }
}
