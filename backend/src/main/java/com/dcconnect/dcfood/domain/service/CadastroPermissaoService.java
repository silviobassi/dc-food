package com.dcconnect.dcfood.domain.service;

import com.dcconnect.dcfood.domain.exception.PermissaoNaoEncontradaException;
import com.dcconnect.dcfood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcconnect.dcfood.domain.model.Permissao;

@Service
public class CadastroPermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public Permissao buscarOuFalhar(Long permissaoId) {
		return permissaoRepository.findById(permissaoId)
			.orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
	}
	
}
