package org.apiconsum.crud.Controller;

import org.apiconsum.crud.Entidade.Venda;
import org.apiconsum.crud.Repository.VendaRepository; // Certifique-se de que você tenha um repositório criado
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/api/vendas")
public class VendaController {
    @Autowired
    private VendaRepository vendaRepository;

    @GetMapping
    public ResponseEntity<List<Venda>> getAllVendas() {
        List<Venda> vendas = vendaRepository.findAll();
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> getVendaById(@PathVariable Long id) {
        Optional<Venda> venda = vendaRepository.findById(id);
        return venda.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/registrarVenda")
    public ResponseEntity<String> createVenda(@RequestBody Venda venda) {
        // Verifica se o CPF já está cadastrado
        if (vendaRepository.findByCpf(venda.getCpf()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já cadastrado. Por favor, insira um CPF diferente.");
        }

        Venda createdVenda = vendaRepository.save(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body("Venda registrada com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> updateVenda(@PathVariable Long id, @RequestBody Venda venda) {
        if (!vendaRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        venda.setId(id); // Define o ID para a venda que está sendo atualizada
        Venda updatedVenda = vendaRepository.save(venda);
        return new ResponseEntity<>(updatedVenda, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenda(@PathVariable Long id) {
        if (!vendaRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        vendaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
