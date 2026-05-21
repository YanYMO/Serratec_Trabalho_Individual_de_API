package org.serratec.praxis.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.serratec.praxis.domain.Aluno;
import org.serratec.praxis.domain.Curso;
import org.serratec.praxis.domain.Matricula;
import org.serratec.praxis.domain.Professor;
import org.serratec.praxis.enums.TipoCurso;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@JsonPropertyOrder({
        "id",
        "nome",
        "descricao",
        "dataInicio",
        "duracaoEmHoras",
        "tipo",
        "professores",
        "matriculas"
})

public class CursoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private Integer duracaoEmHoras;
    private TipoCurso tipo;
    private List<String> professores;
    private List<String> alunos;

    public CursoResponseDTO() {
        super();
    }

    public CursoResponseDTO(Curso curso) {
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.descricao = curso.getDescricao();
        this.dataInicio = curso.getDataInicio();
        this.duracaoEmHoras = curso.getDuracaoEmHoras();
        this.tipo = curso.getTipo();
        this.professores = curso.getProfessores() == null ? Collections.emptyList() :
                curso.getProfessores().stream().map(Professor::getNome).toList();
        this.alunos = curso.getMatriculas() == null ? Collections.emptyList() :
                curso.getMatriculas().stream().map(Matricula::getAluno).map(Aluno::getNome).toList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getProfessores() {
        return professores;
    }

    public void setProfessores(List<String> professores) {
        this.professores = professores;
    }

    public List<String> getMatriculas() {
        return alunos;
    }

    public void setMatriculas(List<String> matriculas) {
        this.alunos = matriculas;
    }
}
