package org.serratec.praxis.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.serratec.praxis.enums.Genero;
import org.serratec.praxis.enums.NivelEscolaridade;
import org.serratec.praxis.enums.RendaFamiliar;

@Entity
@Table(name = "perfil_social")
public class PerfilSocial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Enumerated(EnumType.STRING)
    @Column(name = "genero", nullable = false)
    private Genero genero;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "nivel_de_escolariade", nullable = false)
    private NivelEscolaridade escolaridade;

    @NotBlank(message = "O campo precisa ser preenchido")
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "renda_familiar", nullable = false)
    private RendaFamiliar rendaFamiliar;

    @OneToOne(mappedBy = "perfilSocial")
    private Aluno aluno;
}
