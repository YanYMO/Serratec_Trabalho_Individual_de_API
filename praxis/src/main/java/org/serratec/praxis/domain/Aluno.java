package org.serratec.praxis.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Size(max = 80)
    @Column(name = "nome_aluno", nullable = false, length = 80)
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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "perfil_social_id")
    private PerfilSocial perfilSocial;

}
