package org.serratec.praxis.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.serratec.praxis.enums.StatusMatricula;

import java.time.LocalDate;

@Entity
@Table(name = "matricula")
public class Matricula {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo precisa ser preenchido")
    @Max(99999)
    @Column(name = "codigo", nullable = false, length = 99999)
    private Integer codigo;

    @CreationTimestamp
    @Column(name = "data_de_matricula", updatable = false)
    private LocalDate dataMAtricula;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusMatricula status;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;
}
