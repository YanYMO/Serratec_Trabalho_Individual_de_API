package org.serratec.praxis.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "professor")
public class Professor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(max = 80)
    @Column(name = "nome_professor", nullable = false, length = 80)
    private String nome;

    @NotBlank(message = "O campo precisa ser preenchido")
    @CPF
    @Column(name = "cpf", nullable = false)
    private String cpf;

    @NotNull(message = "O campo precisa ser preenchido")
    @Past
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(max = 60)
    @Email
    @Column(name = "email", nullable = false, length = 60)
    private String email;

    @ManyToMany(mappedBy = "professores")
    private List<Curso> cursos;
}
