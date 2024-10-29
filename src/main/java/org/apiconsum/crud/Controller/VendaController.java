package org.apiconsum.crud.Controller;


import org.apiconsum.crud.Entidade.Venda;
import org.apiconsum.crud.Repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;

    @GetMapping
    public List<Venda> listarTodas() {
        return vendaRepository.findAll();
    }

    @PostMapping
    public Venda registrarVenda(@RequestBody Venda venda) {
        return vendaRepository.save(venda);
    }

    @GetMapping("/{id}")
    public Venda obterVendaPorId(@PathVariable Long id) {
        return vendaRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        vendaRepository.deleteById(id);
    }
}
