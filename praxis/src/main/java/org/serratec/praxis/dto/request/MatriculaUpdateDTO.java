package org.serratec.praxis.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import org.serratec.praxis.enums.StatusMatricula;

public class MatriculaUpdateDTO {

    @NotNull(message = "O campo precisa ser preenchido")
    @Enumerated(EnumType.STRING)
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
