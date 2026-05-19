package org.serratec.praxis.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.serratec.praxis.exception.EnumValidationException;

public enum TipoCurso {
    EAD,
    PRESENCIAL,
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
