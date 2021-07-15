package com.dcconnect.dcfood.domain.service;

import com.dcconnect.dcfood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

	byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
	
}
