package com.maurolage.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.maurolage.cursomc.domain.Cliente;
import com.maurolage.cursomc.domain.enums.TipoCliente;
import com.maurolage.cursomc.dto.ClienteNewDTO;
import com.maurolage.cursomc.repositories.ClienteRepository;
import com.maurolage.cursomc.resources.exceptions.FieldMessage;
import com.maurolage.cursomc.services.validation.utils.BR; 
 
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> { 
 
	@Autowired
	private ClienteRepository repo;
	
    @Override 
    public void initialize(ClienteInsert ann) {     }

    @Override     
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) { 
 
        List<FieldMessage> list = new ArrayList<>(); 
        
        if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj()))  {
        	list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
        }
        
        if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj()))  {
        	list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
        }
        
        Cliente aux = repo.findByEmail(objDto.getEmail());
        if (aux != null) {
        	list.add(new FieldMessage("email", "Email já existe"));
        }

        aux = repo.findByCpfOuCnpj(objDto.getCpfOuCnpj());
        if (aux != null) {
        	list.add(new FieldMessage("cpfOuCnpj", "CPF/CNPJ já existe"));
        }

        for (FieldMessage e : list) {             
        	context.disableDefaultConstraintViolation();             
        	context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
        			.addConstraintViolation();         
        }         
        return list.isEmpty();     
    } 
}
