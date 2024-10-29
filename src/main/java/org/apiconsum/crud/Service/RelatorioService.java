package Service;


import org.apiconsum.crud.Repository.FornecedorRepository;
import org.apiconsum.crud.Repository.ProdutoRepository;
import org.apiconsum.crud.Repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RelatorioService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    // Gera um relatório com informações agregadas sobre vendas
    public Map<String, Object> gerarRelatorioVendas() {
        Map<String, Object> relatorio = new HashMap<>();
        long totalVendas = vendaRepository.count();
        double valorTotalVendas = vendaRepository.findAll()
                .stream()
                .mapToDouble(venda -> venda.getValorTotal())
                .sum();

        relatorio.put("totalVendas", totalVendas);
        relatorio.put("valorTotalVendas", valorTotalVendas);

        return relatorio;
    }

    // Gera um relatório com informações sobre o estoque de produtos
    public Map<String, Object> gerarRelatorioEstoque() {
        Map<String, Object> relatorio = new HashMap<>();
        long totalProdutos = produtoRepository.count();
        int quantidadeTotalEstoque = produtoRepository.findAll()
                .stream()
                .mapToInt(produto -> produto.getQuantidadeEstoque())
                .sum();

        relatorio.put("totalProdutos", totalProdutos);
        relatorio.put("quantidadeTotalEstoque", quantidadeTotalEstoque);

        return relatorio;
    }

    // Gera um relatório sobre os fornecedores cadastrados
    public Map<String, Object> gerarRelatorioFornecedores() {
        Map<String, Object> relatorio = new HashMap<>();
        long totalFornecedores = fornecedorRepository.count();

        relatorio.put("totalFornecedores", totalFornecedores);

        return relatorio;
    }


























}
