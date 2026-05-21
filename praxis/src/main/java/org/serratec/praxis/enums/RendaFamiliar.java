package org.serratec.praxis.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.serratec.praxis.exception.EnumValidationException;

public enum RendaFamiliar {
    CLASSE_E(1, "Até 1.580 reais"),
    CLASSE_D(2, "Entre 1.581 e 2.525"),
    CLASSE_C(3, "Entre 2.526 e 10.885"),
    CLASSE_B(4, "Entre 10.886 e 25.000"),
    CLASSE_A(5, "Acima de 25.000");

    private Integer codigo;
    private String renda;

    private RendaFamiliar(Integer codigo, String rendaFamiliar) {
        this.codigo = codigo;
        this.renda = rendaFamiliar;
    }

    @JsonCreator
    public static RendaFamiliar verifica(Integer value) throws EnumValidationException {
        for (RendaFamiliar r : values()) {
            if (value.equals(r.getCodigo())) {
                return r;
            }
        }
        throw new EnumValidationException(
                "Renda Inválida. Valores válidos: 1 - Até 1.580 reais, 2 - Entre 1.581 e 2.525, 3 - Entre 2.526 e 10.885, 4 - Entre 10.886 e 25.000, 5 - Acima de 25.000.");
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getRenda() {
        return renda;
    }
}
