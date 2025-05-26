package com.curso.domains;

import com.curso.domains.dtos.ProdutoDTO;
import com.curso.domains.enums.Status;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_produto")
    private Long idProduto;

    @NotBlank @NotNull
    private String codigoBarra;

    @NotBlank @NotNull
    private String descricao;

    @NotNull
    @Digits(integer = 15, fraction = 3)
    private BigDecimal saldoEstoque;

    @NotNull
    @Digits(integer = 15, fraction = 3)
    private BigDecimal valorUnitario;

    @NotNull
    @Digits(integer = 15, fraction = 3)
    private BigDecimal valorEstoque;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @ManyToOne
    @JoinColumn(name="idgrupoproduto")
    private GrupoProduto grupoProduto;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name="status")
    private Status status;

    public Produto() {
        this.saldoEstoque = BigDecimal.ZERO;
        this.valorUnitario = BigDecimal.ZERO;
        this.valorEstoque = BigDecimal.ZERO;
        this.status = Status.ATIVO;
    }

    public Produto(Long idProduto, String codigoBarra, String descricao, BigDecimal saldoEstoque, BigDecimal valorUnitario, LocalDate dataCadastro, GrupoProduto grupoProduto, Status status) {
        this.idProduto = idProduto;
        this.codigoBarra = codigoBarra;
        this.descricao = descricao;
        this.saldoEstoque = saldoEstoque;
        this.valorUnitario = valorUnitario;
        this.dataCadastro = dataCadastro;
        this.grupoProduto = grupoProduto;
        this.status = status;

        this.valorEstoque = saldoEstoque.multiply(valorUnitario)
                .setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public Produto(ProdutoDTO dto) {
        this.idProduto = dto.getIdProduto();
        this.codigoBarra = dto.getCodigoBarra();
        this.descricao = dto.getDescricao();
        this.saldoEstoque = dto.getSaldoEstoque();
        this.valorUnitario = dto.getValorUnitario();
        this.dataCadastro = dto.getDataCadastro();
        this.status = Status.toEnum(dto.getStatus());
        this.grupoProduto = new GrupoProduto();
        this.grupoProduto.setId(dto.getGrupoProduto());

        this.valorEstoque = saldoEstoque.multiply(valorUnitario)
                .setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public @NotBlank @NotNull String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(@NotBlank @NotNull String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public @NotBlank @NotNull String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank @NotNull String descricao) {
        this.descricao = descricao;
    }

    public @NotNull @Digits(integer = 15, fraction = 3) BigDecimal getSaldoEstoque() {
        return saldoEstoque;
    }

    public void setSaldoEstoque(@NotNull @Digits(integer = 15, fraction = 3) BigDecimal saldoEstoque) {
        this.saldoEstoque = saldoEstoque;
    }

    public @NotNull @Digits(integer = 15, fraction = 3) BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(@NotNull @Digits(integer = 15, fraction = 3) BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public @NotNull @Digits(integer = 15, fraction = 3) BigDecimal getValorEstoque() {
        return valorEstoque;
    }

    public void setValorEstoque(@NotNull @Digits(integer = 15, fraction = 3) BigDecimal valorEstoque) {
        this.valorEstoque = valorEstoque;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public GrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(GrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return idProduto == produto.idProduto && Objects.equals(descricao, produto.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduto, descricao);
    }
}
