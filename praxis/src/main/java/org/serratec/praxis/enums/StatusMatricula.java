package org.serratec.praxis.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.serratec.praxis.exception.EnumValidationException;

public enum StatusMatricula {
    ATIVA,
    SUSPENSA,
    CANCELADA;

    @JsonCreator
    public static StatusMatricula verifica(String value) throws EnumValidationException {
        for (StatusMatricula S : values()) {
            if( value.equals(S.name())) {
                return S;
            }
        }
        throw new EnumValidationException(
                "Categoria inválida. Valores válidos: EAD, PRESENCIAL, SEMI_PRESENCIAL");
    }
}
