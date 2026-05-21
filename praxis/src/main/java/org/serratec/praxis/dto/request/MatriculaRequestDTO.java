package org.serratec.praxis.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.serratec.praxis.enums.StatusMatricula;

public class MatriculaRequestDTO {

    @NotNull(message = "O campo precisa ser preenchido")
    @Max(99999)
    @Positive
    @Schema(description = "Codigo da Matrícula", example = "12345")
    private Integer codigo;

    @NotNull(message = "O campo precisa ser preenchido")
    @Schema(description = "Status da Matrícula", example = "ATIVA, SUSPENSA, CANCELADA")
    private StatusMatricula status;

    @NotNull(message = "O campo precisa ser preenchido")
    @Schema(description = "O identidicador do Aluno já cadastrado", example = "1")
    private Long alunoId;

    @NotNull(message = "O campo precisa ser preenchido")
    @Schema(description = "O identificador do Curso já cadastrado", example = "1")
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
