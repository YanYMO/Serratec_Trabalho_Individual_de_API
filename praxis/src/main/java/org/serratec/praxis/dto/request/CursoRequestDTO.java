package org.serratec.praxis.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.serratec.praxis.enums.TipoCurso;

import java.time.LocalDate;

@Schema(description = "Dados necessários para cadastrar ou atualizar um Curso")
public class CursoRequestDTO {

    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(max = 30)
    @Schema(description = "Nome completo do Curso", example = "API")
    private String nome;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(max = 200)
    @Schema(description = "Descrição completa do Curso", example = "Curso de Api")
    private String descricao;

    @NotNull(message = "O campo precisa ser preenchido")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Schema(description = "Data de início do Curso", example = "20/05/2026")
    private LocalDate dataInicio;

    @NotNull(message = "O campo precisa ser preenchido")
    @Schema(description = "Duração em horas do Curso", example = "150")
    private Integer duracaoEmHoras;

    @NotNull(message = "O campo precisa ser preenchido")
    @Schema(description = "Tipo do Curso", example = "EAD, PRESENCIAL, SEMI_PRESENCIAL")
    private TipoCurso tipo;

    public CursoRequestDTO(String nome, String descricao, LocalDate dataInicio, Integer duracaoEmHoras, TipoCurso tipo) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.duracaoEmHoras = duracaoEmHoras;
        this.tipo = tipo;
    }

    public CursoRequestDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Integer getDuracaoEmHoras() {
        return duracaoEmHoras;
    }

    public void setDuracaoEmHoras(Integer duracaoEmHoras) {
        this.duracaoEmHoras = duracaoEmHoras;
    }

    public TipoCurso getTipo() {
        return tipo;
    }

    public void setTipo(TipoCurso tipo) {
        this.tipo = tipo;
    }
}
