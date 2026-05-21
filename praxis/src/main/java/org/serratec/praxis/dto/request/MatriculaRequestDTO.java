package org.serratec.praxis.dto.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import org.serratec.praxis.enums.StatusMatricula;

public class MatriculaRequestDTO {

    @NotNull(message = "O campo precisa ser preenchido")
    @Max(99999)
    private Integer codigo;

    @NotNull(message = "O campo precisa ser preenchido")
    @Enumerated(EnumType.STRING)
    private StatusMatricula status;

    @NotNull(message = "O campo precisa ser preenchido")
    private Long alunoId;

    @NotNull(message = "O campo precisa ser preenchido")
    private Long cursoId;

    public MatriculaRequestDTO(Integer codigo, StatusMatricula status, Long alunoId, Long cursoId) {
        this.codigo = codigo;
        this.status = status;
        this.alunoId = alunoId;
        this.cursoId = cursoId;
    }

    public MatriculaRequestDTO() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public StatusMatricula getStatus() {
        return status;
    }

    public void setStatus(StatusMatricula status) {
        this.status = status;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
}
