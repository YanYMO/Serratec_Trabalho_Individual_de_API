package org.serratec.praxis.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.serratec.praxis.enums.TipoCurso;

import java.time.LocalDate;
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
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "data_de_inicio", nullable = false)
    private LocalDate dataInicio;

    @NotNull(message = "O campo precisa ser preenchido")
    @Column(name = "duracao_em_horas", nullable = false)
    private Integer duracaoEmHoras;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoCurso tipo;

    @OneToMany(mappedBy = "curso")
    private List<Matricula> matriculas;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "curso_professor",
    joinColumns = @JoinColumn(name = "id_curso"),
    inverseJoinColumns = @JoinColumn(name = "id_professor"))
    private List<Professor> professores;
}
