package br.com.anhembi.bradesco.validaCnpj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.anhembi.bradesco.validaCnpj.model.EmpresaValidadora;

@Repository
public interface EmpresaValidadoraRepository extends JpaRepository<EmpresaValidadora, Long> {

}
