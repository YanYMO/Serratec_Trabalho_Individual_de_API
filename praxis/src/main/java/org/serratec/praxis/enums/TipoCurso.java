package org.serratec.praxis.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.serratec.praxis.exception.EnumValidationException;

@Schema(description = "Modalidade do Curso")
public enum TipoCurso {

    @Schema(description = "EAD")
    EAD,
    @Schema(description = "Presencial")
    PRESENCIAL,
    @Schema(description = "Semi-Presencial")
    SEMI_PRESENCIAL;

    @JsonCreator
    public static TipoCurso verifica(String value) throws EnumValidationException {
        for (TipoCurso T : values()) {
            if( value.equals(T.name())) {
                return T;
            }
        }
        throw new EnumValidationException(
                "Categoria inválida. Valores válidos: EAD, PRESENCIAL, SEMI_PRESENCIAL");
    }
}
