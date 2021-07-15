package com.dcconnect.dcfood.domain.event;

import com.dcconnect.dcfood.domain.model.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoConfirmadoEvent {

	private Pedido pedido;
	
}
