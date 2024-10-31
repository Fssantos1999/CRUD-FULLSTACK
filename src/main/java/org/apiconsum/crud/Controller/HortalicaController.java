package org.apiconsum.crud.Controller;

import org.apiconsum.crud.Entidade.Hortalica;
import org.apiconsum.crud.Repository.HortalicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/api/hortalicas")
public class HortalicaController {

    @Autowired
    private HortalicaRepository hortalicaRepository;

    @GetMapping
    public List<Hortalica> listarHortalicas() {
        return hortalicaRepository.findAll();
    }

    @PostMapping("/adicionarHortalica")
    public Hortalica adicionarHortalica(@RequestBody Hortalica hortalica) {
        return hortalicaRepository.save(hortalica);
    }

    @PutMapping("atualizarHortalica/{id}")
    public Hortalica atualizarHortalica(@PathVariable Long id, @RequestBody Hortalica hortalicaAtualizada) {
        return hortalicaRepository.findById(id)
                .map(hortalica -> {
                    hortalica.setNome(hortalicaAtualizada.getNome());
                    hortalica.setMedidasProtetivas(hortalicaAtualizada.getMedidasProtetivas());
                    hortalica.setTipoSoloPH(hortalicaAtualizada.getTipoSoloPH());
                    hortalica.setMelhorEpoca(hortalicaAtualizada.getMelhorEpoca());
                    hortalica.setPreco(hortalicaAtualizada.getPreco());
                    return hortalicaRepository.save(hortalica);
                }).orElse(null); // Retorna null se não encontrar a hortalica
    }

    @DeleteMapping("/{id}")
    public void removerHortalica(@PathVariable Long id) {
        hortalicaRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Hortalica obterHortalicaPorId(@PathVariable Long id) {
        return hortalicaRepository.findById(id).orElse(null);
    }

}
