package org.serratec.praxis.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.serratec.praxis.exception.EnumValidationException;

@Schema(description = "Status da matrícula do Aluno")
public enum StatusMatricula {

    @Schema(description = "Ativa")
    ATIVA,
    @Schema(description = "Suspensa")
    SUSPENSA,
    @Schema(description = "Cancelada")
    CANCELADA;

    @JsonCreator
    public static StatusMatricula verifica(String value) throws EnumValidationException {
        for (StatusMatricula S : values()) {
            if( value.equals(S.name())) {
                return S;
            }
        }
        throw new EnumValidationException(
                "Categoria inválida. Valores válidos: ATIVA, SUSPENSA, CANCELADA");
    }
}
