package org.apiconsum.crud.Controller;

import Service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/api/relatorios")
public class RelatorioController {

  RelatorioService relatorioService;
    @GetMapping("/vendas")
    public Map<String, Object> gerarRelatorioVendas() {
        return relatorioService.gerarRelatorioVendas();
    }

    @GetMapping("/estoque")
    public Map<String, Object> gerarRelatorioEstoque() {
        return relatorioService.gerarRelatorioEstoque();
    }

    @GetMapping("/fornecedores")
    public Map<String, Object> gerarRelatorioFornecedores() {
        return relatorioService.gerarRelatorioFornecedores();
    }
}
