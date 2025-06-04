package br.com.anhembi.bradesco.validaCnpj.utils;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.anhembi.bradesco.validaCnpj.dto.AnaliseEntidadeDTO;
import br.com.anhembi.bradesco.validaCnpj.dto.EntidadeConsultaDTO;
import br.com.anhembi.bradesco.validaCnpj.model.AnaliseEntidade;
import br.com.anhembi.bradesco.validaCnpj.model.AnaliseCredito;

@Component
public  class MapperUtils {
		
	
    private static ModelMapper modelMapper = new ModelMapper();
	
	public static List<AnaliseEntidadeDTO> convertToDTO(List<AnaliseEntidade> entidade) {
		List<AnaliseEntidadeDTO> retorno = new ArrayList<AnaliseEntidadeDTO>();
		
		entidade.forEach(e->{
			retorno.add(modelMapper.map(e, AnaliseEntidadeDTO.class));
		});
		
		return retorno;
	}
	public static EntidadeConsultaDTO convertToDTO(AnaliseCredito entidade) {
		return modelMapper.map(entidade, EntidadeConsultaDTO.class);
	}
	
	public static AnaliseCredito convertToModel(Long id, EntidadeConsultaDTO dto) {
		AnaliseCredito response = modelMapper.map(dto,AnaliseCredito.class);
		response.setId(id);
		return response;
	}
}
