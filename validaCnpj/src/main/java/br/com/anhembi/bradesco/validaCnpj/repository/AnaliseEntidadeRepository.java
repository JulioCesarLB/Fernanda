package br.com.anhembi.bradesco.validaCnpj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.anhembi.bradesco.validaCnpj.model.AnaliseEntidade;
import br.com.anhembi.bradesco.validaCnpj.model.EmpresaValidadora;
import br.com.anhembi.bradesco.validaCnpj.model.AnaliseCredito;

@Repository
public interface AnaliseEntidadeRepository extends JpaRepository<AnaliseEntidade, Long> {	 	
		List<AnaliseEntidade> findAllByEntidade(AnaliseCredito entidade);

}
