package br.com.anhembi.bradesco.validaCnpj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.anhembi.bradesco.validaCnpj.model.AnaliseCredito;
import br.com.anhembi.bradesco.validaCnpj.model.AnaliseEntidade;

@Repository
public interface AnaliseEntidadeRepository extends JpaRepository<AnaliseEntidade, Long> {	 	
		List<AnaliseEntidade> findAllByCredito(AnaliseCredito credito);

}
