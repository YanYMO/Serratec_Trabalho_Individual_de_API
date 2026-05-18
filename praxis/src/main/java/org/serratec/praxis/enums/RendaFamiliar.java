package org.serratec.praxis.enums;

public enum RendaFamiliar {
    CLASSE_E(1, "Até 1.580 reais"),
    CLASSE_D(2, "Entre 1.581 e 2.525"),
    CLASSE_C(3, "Entre 2.526 e 10.885"),
    CLASSE_B(4, "Entre 10.886 e 25.000"),
    CLASSE_A(5, "Acima de 25.000");

    private Integer codigo;
    private String rendaFamiliar;

    RendaFamiliar(Integer codigo, String rendaFamiliar) {
        this.codigo = codigo;
        this.rendaFamiliar = rendaFamiliar;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getRendaFamiliar() {
        return rendaFamiliar;
    }

    public void setRendaFamiliar(String rendaFamiliar) {
        this.rendaFamiliar = rendaFamiliar;
    }
}
