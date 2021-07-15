package com.dcconnect.dcfood.domain.repository;

import com.dcconnect.dcfood.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {

	FotoProduto save(FotoProduto foto);
	
	void delete(FotoProduto foto);
	
}
