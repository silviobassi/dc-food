package com.dcconnect.dcfood.api.v1.controller;

import com.dcconnect.dcfood.api.v1.assembler.FormaPagamentoModelAssembler;
import com.dcconnect.dcfood.core.security.AlgaSecurity;
import com.dcconnect.dcfood.core.security.CheckSecurity;
import com.dcconnect.dcfood.domain.model.Restaurante;
import com.dcconnect.dcfood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dcconnect.dcfood.api.v1.AlgaLinks;
import com.dcconnect.dcfood.api.v1.model.FormaPagamentoModel;
import com.dcconnect.dcfood.api.v1.openapi.controller.RestauranteFormaPagamentoControllerOpenApi;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/formas-pagamento",
		produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@Override
	@GetMapping
	public CollectionModel<FormaPagamentoModel> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		CollectionModel<FormaPagamentoModel> formasPagamentoModel 
			= formaPagamentoModelAssembler.toCollectionModel(restaurante.getFormasPagamento())
				.removeLinks();
		
		formasPagamentoModel.add(algaLinks.linkToRestauranteFormasPagamento(restauranteId));

		if (algaSecurity.podeGerenciarFuncionamentoRestaurantes(restauranteId)) {
			formasPagamentoModel.add(algaLinks.linkToRestauranteFormaPagamentoAssociacao(restauranteId, "associar"));
			
			formasPagamentoModel.getContent().forEach(formaPagamentoModel -> {
				formaPagamentoModel.add(algaLinks.linkToRestauranteFormaPagamentoDesassociacao(
						restauranteId, formaPagamentoModel.getId(), "desassociar"));
			});
		}
		
		return formasPagamentoModel;
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@Override
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestaurante.desassociarFormaPagamento(restauranteId, formaPagamentoId);
		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@Override
	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestaurante.associarFormaPagamento(restauranteId, formaPagamentoId);
		
		return ResponseEntity.noContent().build();
	}

}
