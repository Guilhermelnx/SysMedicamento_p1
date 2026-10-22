package models;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Medicamento {
    private String codigo;
    private String Nome;
    private String descricao;
    private String principioAtivo;
    private LocalDate dataValidade;
    private int quantidadeEstoque;
    private boolean controlado;
    private Fornecedor fornecedor;
    private BigDecimal preco;


    //Construtores

    public Medicamento(String codigo, String nome, String descricao, String principioAtivo, LocalDate dataValidade, int quantidadeEstoque, boolean controlado, Fornecedor fornecedor) {
        if (codigo == null || codigo.trim().isEmpty()) {
            this.codigo = UUID.randomUUID().toString();
        } else {
            this.codigo = codigo;
        }
        Nome = nome;
        this.descricao = descricao;
        this.principioAtivo = principioAtivo;
        this.dataValidade = dataValidade;
        this.quantidadeEstoque = quantidadeEstoque;
        this.controlado = controlado;
        this.fornecedor = fornecedor;
        //this.preco = preco;
    }


    // gettes and setters


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrincipioAtivo() {
        return principioAtivo;
    }

    public void setPrincipioAtivo(String principioAtivo) {
        this.principioAtivo = principioAtivo;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public boolean isControlado() {
        return controlado;
    }

    public void setControlado(boolean controlado) {
        this.controlado = controlado;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
