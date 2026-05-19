package org.serratec.praxis.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.serratec.praxis.exception.EnumValidationException;

public enum Genero {
    MASCULINO,
    FEMININO,
    OUTROS;

    @JsonCreator
    public static Genero verifica(String value) throws EnumValidationException {
        for (Genero g : values()) {
            if(value.equals(g.name())) {
                return g;
            }
        }
        throw new EnumValidationException(
                "Genero Inválido. Valores válidos: MASCULINO, FEMININO, OUTROS");
    }
}
