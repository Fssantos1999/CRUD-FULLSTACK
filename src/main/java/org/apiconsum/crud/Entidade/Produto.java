package org.apiconsum.crud.Entidade;

import jakarta.persistence.*;

@Entity
@Table
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private double preco;
    @Column(nullable = false)
    private int quantidadeEstoque;
    @Column(nullable = false)
    private String TipodeHortalica;
    @Column(nullable = false)
    private String Categoria;
    @Column
   private String Fornecedor;

    public Produto(long id, String nome, String descricao,
                   double preco, int quantidadeEstoque,
                   String tipodeHortalica, String categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        TipodeHortalica = tipodeHortalica;
        Categoria = categoria;
    }

    public Produto() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getTipodeHortalica() {
        return TipodeHortalica;
    }

    public void setTipodeHortalica(String tipodeHortalica) {
        TipodeHortalica = tipodeHortalica;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }
}
