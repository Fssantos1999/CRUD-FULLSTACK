package org.apiconsum.crud.Entidade;

import jakarta.persistence.*;

@Entity
@Table
public class Hortalica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String medidasProtetivas;

    @Column(nullable = false)
    private String tipoSoloPH;

    @Column(nullable = false)
    private String melhorEpoca;

    @Column(nullable = false)
    private double preco;


    public Hortalica(Long id, String nome, String medidasProtetivas, String tipoSoloPH, String melhorEpoca, double preco) {
        this.id = id;
        this.nome = nome;
        this.medidasProtetivas = medidasProtetivas;
        this.tipoSoloPH = tipoSoloPH;
        this.melhorEpoca = melhorEpoca;
        this.preco = preco;
    }


    public Hortalica() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMedidasProtetivas() {
        return medidasProtetivas;
    }

    public void setMedidasProtetivas(String medidasProtetivas) {
        this.medidasProtetivas = medidasProtetivas;
    }

    public String getTipoSoloPH() {
        return tipoSoloPH;
    }

    public void setTipoSoloPH(String tipoSoloPH) {
        this.tipoSoloPH = tipoSoloPH;
    }

    public String getMelhorEpoca() {
        return melhorEpoca;
    }

    public void setMelhorEpoca(String melhorEpoca) {
        this.melhorEpoca = melhorEpoca;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
