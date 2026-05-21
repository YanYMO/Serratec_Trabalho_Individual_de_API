package org.serratec.praxis.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.serratec.praxis.enums.TipoCurso;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "curso")
public class Curso {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(max = 30)
    @Column(name = "nome_curso", nullable = false, length = 30)
    private String nome;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(max = 200)
    @Column(name = "descricao", nullable = false, length = 200)
    private String descricao;

    @NotNull(message = "O campo precisa ser preenchido")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "data_de_inicio", nullable = false)
    private LocalDate dataInicio;

    @NotNull(message = "O campo precisa ser preenchido")
    @Column(name = "duracao_em_horas", nullable = false)
    private Integer duracaoEmHoras;

    @NotNull(message = "O campo precisa ser preenchido")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoCurso tipo;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "curso_professor",
            joinColumns = @JoinColumn(name = "id_curso"),
            inverseJoinColumns = @JoinColumn(name = "id_professor"))
    private List<Professor> professores = new ArrayList<>();

    @OneToMany(mappedBy = "curso")
    private List<Matricula> matriculas = new ArrayList<>();

    public Curso(Long id, String nome, String descricao, LocalDate dataInicio, Integer duracaoEmHoras, TipoCurso tipo, List<Matricula> matriculas, List<Professor> professores) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.duracaoEmHoras = duracaoEmHoras;
        this.tipo = tipo;
        this.matriculas = matriculas;
        this.professores = professores;
    }

    public Curso() {
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

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }
}
