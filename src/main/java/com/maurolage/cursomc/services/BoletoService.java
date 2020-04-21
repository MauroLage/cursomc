package com.maurolage.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.maurolage.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanceDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanceDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 30);
		pagto.setDataVencimento(cal.getTime());
	}
}
