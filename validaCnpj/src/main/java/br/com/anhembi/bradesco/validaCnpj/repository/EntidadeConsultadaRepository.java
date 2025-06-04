package br.com.anhembi.bradesco.validaCnpj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.anhembi.bradesco.validaCnpj.model.AnaliseCredito;


@Repository
public interface EntidadeConsultadaRepository extends JpaRepository<AnaliseCredito, Long>{
	public List<AnaliseCredito> findByCpfCnpj(String cpfCnpj);

}
