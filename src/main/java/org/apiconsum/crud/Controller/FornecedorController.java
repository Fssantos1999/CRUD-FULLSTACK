package org.apiconsum.crud.Controller;

import org.apiconsum.crud.Entidade.Fornecedor;
import org.apiconsum.crud.Repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/api/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    // Endpoint para criar um novo fornecedor
    @PostMapping("/adicionarFornecedor")
    public Fornecedor criarFornecedor(@RequestBody Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    // Endpoint para obter todos os fornecedores
    @GetMapping("/listartodos")
    public Iterable<Fornecedor> listarFornecedores() {
        return  fornecedorRepository.findAll();
    }

    // Endpoint para obter um fornecedor por ID
    @GetMapping("/localizarFornecedor/{id}")
    public Fornecedor obterFornecedorPorId(@PathVariable Long id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
    }

    // Endpoint para atualizar um fornecedor existente
    @PutMapping("atualizarFornecedor/{id}")
    public Fornecedor atualizarFornecedor(@PathVariable Long id, @RequestBody Fornecedor fornecedor) {
        return fornecedorRepository.findById(id).map(fornecedorExistente -> {
            fornecedor.setId(id);
            return fornecedorRepository.save(fornecedor);
        }).orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
    }

    // Endpoint para deletar um fornecedor
    @DeleteMapping("/{id}")
    public void deletarFornecedor(@PathVariable Long id) {
        if (fornecedorRepository.existsById(id)) {
            fornecedorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Fornecedor não encontrado");
        }
    }
}
