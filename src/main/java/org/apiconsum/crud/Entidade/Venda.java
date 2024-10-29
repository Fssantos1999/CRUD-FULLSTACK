package org.apiconsum.crud.Entidade;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long idCliente;
    @Column(nullable = false)
    private LocalDate dataVenda;
    @Column(nullable = false)
    private double valorTotal;


    public Venda(Long id, Long idCliente, LocalDate dataVenda, double valorTotal) {
        this.id = id;
        this.idCliente = idCliente;
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
    }

    public Venda() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
