package org.serratec.praxis.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.serratec.praxis.enums.TipoCurso;

import java.time.LocalDate;

public class CursoRequestDTO {

    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(max = 30)
    private String nome;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(max = 200)
    private String descricao;

    @NotNull(message = "O campo precisa ser preenchido")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataInicio;

    @NotNull(message = "O campo precisa ser preenchido")
    private Integer duracaoEmHoras;

    @NotNull(message = "O campo precisa ser preenchido")
    @Enumerated(EnumType.STRING)
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
