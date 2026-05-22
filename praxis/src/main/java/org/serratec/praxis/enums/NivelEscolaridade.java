package org.serratec.praxis.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.serratec.praxis.exception.EnumValidationException;

@Schema(description = "Nível de escolaridade do aluno")
public enum NivelEscolaridade {

    @Schema(description = "Fundamental Imcompleto")
    ENSINO_FUNDAMENTAL_INCOMPLETO(1, "Fundamental Imcompleto"),
    @Schema(description = "Fundamental Completo")
    ENSINO_FUNDAMENTAL_COMPLETO(2, "Fundamental Completo"),
    @Schema(description = "Médio Imcompleto")
    ENSINO_MEDIO_IMCOMPLETO(3, "Médio Imcompleto"),
    @Schema(description = "Médio Completo")
    ENSINO_MEDIO_COMPLETO(4, "Médio Completo"),
    @Schema(description = "Superior Imcompleto")
    ENSINO_SUPERIOR_IMCOMPLETO(5, "Superior Imcompleto"),
    @Schema(description = "Superior Completo")
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
                "Escolaridade Inválida. Valores válidos: " +
                        "1 - Fundamental Imcompleto, " +
                        "2 - Fundamental Completo, " +
                        "3 - Médio Imcompleto, " +
                        "4 - Medio Completo, " +
                        "5 - Superior Imcompleto, " +
                        "6 - Superior Completo");
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNivel() {
        return nivel;
    }

}
