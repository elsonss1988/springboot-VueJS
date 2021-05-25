package com.elson.mycash.service;

import com.elson.mycash.domain.Lancamento;
import com.elson.mycash.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Service
public class LancamentoService {
    @Autowired
    private LancamentoRepository repo;

    //public List<Lancamento> todos(){
    //    return repo.findAll();
    //}

    public Page<Lancamento> todos(Pageable pageable){
        return repo.findAll(pageable);
    }

    public Lancamento apenasum(Integer id){
        //System.out.println(id);
        //return null;
        return repo.findById(id).orElseThrow(()->new EntityNotFoundException());
    }

    public Lancamento criar( Lancamento lancamento){
        return repo.save(lancamento);
    }

    public Lancamento atualizar(
            @PathVariable Integer id,
            @RequestBody Lancamento novoLancamento){
        return repo.findById(id).map(lancamento->{
            lancamento.setDescricao(novoLancamento.getDescricao());
            lancamento.setValor(novoLancamento.getValor());
            lancamento.setData(novoLancamento.getData());
            lancamento.setTipo(novoLancamento.getTipo());

            return repo.save(lancamento);
        }).orElseThrow(()-> new EntityNotFoundException());
    }

    public void excluir( Integer id){
        repo.deleteById(id);
    }
}
