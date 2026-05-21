package org.serratec.praxis.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.serratec.praxis.enums.StatusMatricula;

@Schema(description = "Dados necessários para atualizar uma Matrícula")
public class MatriculaUpdateDTO {

    @NotNull(message = "O campo precisa ser preenchido")
    @Schema(description = "Status que será aplicado na Matrícula escolhida", example = "ATIVA, SUSPENSA, CANCELADA")
    private StatusMatricula status;

    public MatriculaUpdateDTO(StatusMatricula status) {
        this.status = status;
    }

    public MatriculaUpdateDTO() {
    }

    public StatusMatricula getStatus() {
        return status;
    }

    public void setStatus(StatusMatricula status) {
        this.status = status;
    }
}
