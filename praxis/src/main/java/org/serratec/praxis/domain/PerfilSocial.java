package org.serratec.praxis.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.serratec.praxis.enums.Genero;
import org.serratec.praxis.enums.NivelEscolaridade;
import org.serratec.praxis.enums.RendaFamiliar;

@Entity
@Table(name = "perfil_social")
public class PerfilSocial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo precisa ser preenchido")
    @Enumerated(EnumType.STRING)
    @Column(name = "genero", nullable = false)
    private Genero genero;

    @NotNull(message = "O campo precisa ser preenchido")
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "nivel_de_escolariade", nullable = false)
    private NivelEscolaridade escolaridade;

    @NotNull(message = "O campo precisa ser preenchido")
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "renda_familiar", nullable = false)
    private RendaFamiliar rendaFamiliar;

    @OneToOne(mappedBy = "perfilSocial")
    @JsonBackReference
    private Aluno aluno;

    public PerfilSocial(Long id, Genero genero, NivelEscolaridade escolaridade, RendaFamiliar rendaFamiliar) {
        this.id = id;
        this.genero = genero;
        this.escolaridade = escolaridade;
        this.rendaFamiliar = rendaFamiliar;
    }

    public PerfilSocial() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public NivelEscolaridade getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(NivelEscolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }

    public RendaFamiliar getRendaFamiliar() {
        return rendaFamiliar;
    }

    public void setRendaFamiliar(RendaFamiliar rendaFamiliar) {
        this.rendaFamiliar = rendaFamiliar;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
