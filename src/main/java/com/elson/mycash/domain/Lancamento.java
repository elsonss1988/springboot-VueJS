package com.elson.mycash.domain;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name="lancamento")
public class Lancamento {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  Integer id;

    @Column(name="ds_lancamento")
    @NotBlank(message="Campo Descricao e' obrigatorio")
    @Size(max=50)
    private String descricao;

    @Column(name="vl_lancamento",precision=8, scale=2)
    @Min(value=0, message ="Campo valor deve ser maior ou igual a zero")
    @PositiveOrZero()
    private Double valor;

    @Column(name="dt_lancamento")
    @FutureOrPresent
    @NotNull(message="Campo data e' obrigatorio")
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message="Campo tipo e' obrigatorio")
//    @Pattern(regexp=".",message="precisa ser um texto")
    private LancamentoTipo tipo;

    public Lancamento() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LancamentoTipo getTipo() {
        return tipo;
    }

    public void setTipo(LancamentoTipo tipo) {
        this.tipo = tipo;
    }
}
