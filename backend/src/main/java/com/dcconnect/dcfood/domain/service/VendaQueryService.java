package com.dcconnect.dcfood.domain.service;

import java.util.List;

import com.dcconnect.dcfood.domain.filter.VendaDiariaFilter;
import com.dcconnect.dcfood.domain.model.dto.VendaDiaria;

public interface VendaQueryService {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
	
}
