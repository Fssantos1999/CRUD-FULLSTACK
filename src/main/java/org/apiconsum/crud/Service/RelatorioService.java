package org.apiconsum.crud.Service;

import org.apiconsum.crud.Entidade.Fornecedor;
import org.apiconsum.crud.Repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    // Gera um relatório sobre os fornecedores cadastrados
    public Map<String, Object> gerarRelatorioFornecedores() {
        Map<String, Object> relatorio = new HashMap<>();

        // Total de fornecedores cadastrados
        long totalFornecedores = fornecedorRepository.count();

        // Lista de informações dos fornecedores
        List<Map<String, String>> fornecedoresInfo = fornecedorRepository.findAll()
                .stream()
                .map(fornecedor -> {
                    Map<String, String> info = new HashMap<>();
                    info.put("nome", fornecedor.getNome() != null ? fornecedor.getNome() : "");
                    info.put("telefone", fornecedor.getTelefone() != null ? fornecedor.getTelefone() : "");
                    info.put("email", fornecedor.getEmail() != null ? fornecedor.getEmail() : "");
                    return info;
                })
                .collect(Collectors.toList());

        // Adiciona os dados ao relatório
        relatorio.put("totalFornecedores", totalFornecedores);
        relatorio.put("fornecedores", fornecedoresInfo);

        return relatorio;
    }














}
