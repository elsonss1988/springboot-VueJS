package com.elson.mycash.web.api;

import com.elson.mycash.domain.Lancamento;
import com.elson.mycash.repository.LancamentoRepository;
import com.elson.mycash.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/lancamento")
public class LancamentoController {

    @Autowired
    private LancamentoService service;
    @GetMapping
    public List<Lancamento> todos(){
        return service.todos();
    }
    @GetMapping("/{id}")
    public Lancamento apenasum(@PathVariable Integer id){
        return service.apenasum(id);
    }

    @PostMapping
    public Lancamento criar( @Valid @RequestBody Lancamento lancamento){
        return service.criar(lancamento);
    }

    @PutMapping("/{id}")
    public Lancamento atualizar(
            @PathVariable Integer id,
            @RequestBody Lancamento novoLancamento){
        return service.atualizar(id,novoLancamento);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id){
        service.excluir(id);
    }
}
