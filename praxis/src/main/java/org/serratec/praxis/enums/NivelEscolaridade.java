package org.serratec.praxis.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.serratec.praxis.exception.EnumValidationException;

public enum NivelEscolaridade {
    ENSINO_FUNDAMENTAL_INCOMPLETO(1, "Fundamental Imcompleto"),
    ENSINO_FUNDAMENTAL_COMPLETO(2, "Fundamental Completo"),
    ENSINO_MEDIO_IMCOMPLETO(3, "Médio Imcompleto"),
    ENSINO_MEDIO_COMPLETO(4, "Médio Completo"),
    ENSINO_SUPERIOR_IMCOMPLETO(5, "Superior Imcompleto"),
    ENSINO_SUPERIOR_COMPLETO(6, "Superior Completo");

    private Integer codigo;
    private String nivel;

    private NivelEscolaridade(Integer codigo, String nivel) {
        this.codigo = codigo;
        this.nivel = nivel;
    }

    @JsonCreator
    public static NivelEscolaridade verifica(Integer value) throws EnumValidationException {
        for (NivelEscolaridade n : values()) {
            if (value.equals(n.getCodigo())) {
                return n;
            }
        }
        throw new EnumValidationException(
                "Escolaridade Inválida. Valores válidos: 1 - Fundamental Imcompleto, 2 - Fundamental Completo, 3 - Médio Imcompleto, 4 - Medio Completo, 5 - Superior Imcompleto, 6 - Superior Completo");
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
