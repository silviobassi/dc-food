package com.dcconnect.dcfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.dcconnect.dcfood.domain.model.Restaurante;

public interface RestauranteRepositoryQueries {

	List<Restaurante> find(String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	List<Restaurante> findComFreteGratis(String nome);

}