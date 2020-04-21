package com.maurolage.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.maurolage.cursomc.domain.Cliente;
import com.maurolage.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

	 void sendNewPasswordEmail(Cliente cliente, String newPass);
}
	