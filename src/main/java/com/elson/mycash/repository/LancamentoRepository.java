package com.elson.mycash.repository;

import com.elson.mycash.domain.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento,Integer> {

}
