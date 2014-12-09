package org.idisoft.restos.test.unit.model.jpa.usuario;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.UsuarioJPA;
import org.junit.Test;

public class BeanValidationTest {
	
	
	private boolean isValid(Usuario usuario)
	{
		ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
		Validator validator=factory.getValidator();
		Set<ConstraintViolation<Usuario>> violations=validator.validate(usuario);
		return violations.isEmpty();
	}
	
	private Usuario validUsuario()
	{
		String validCedula="V12345678";
		String validLogin="abcdef";
		String validPassword="abcd1234";
		
		Usuario retorno=new UsuarioJPA();
		retorno.setCedula(validCedula);
		retorno.setLogin(validLogin);
		retorno.setPassword(validPassword);
		
		return retorno;
	}

	@Test
	public void BeanValidation_UsuarioValid_NoViolations() {
		Usuario validusuario= validUsuario();
		assertTrue(isValid(validusuario));
	}
	
	@Test
	public void BeanValidation_CedulaDoesNotHaveLetter_NotValid()
	{
		Usuario invalidusuario=validUsuario();
		invalidusuario.setCedula("0123456789");
		assertFalse(isValid(invalidusuario));
	}
	
	@Test
	public void BeanValidation_CedulaDoesNotBeginWithLetter_NotValid()
	{
		Usuario invalidusuario=validUsuario();
		invalidusuario.setCedula("123456789V");
		assertFalse(isValid(invalidusuario));
	}
	
	@Test
	public void BeanValidation_FirstLetterIsV_Valid()
	{
		Usuario validusuario=validUsuario();
		validusuario.setCedula("V987654321");
		assertTrue(isValid(validusuario));
	}
	
	@Test
	public void BeanValidation_FirstLetterIsE_Valid()
	{
		Usuario validusuario=validUsuario();
		validusuario.setCedula("E987654321");
		assertTrue(isValid(validusuario));
	}
	
	@Test
	public void BeanValidation_CedulaIsNull_NotValid()
	{
		Usuario invalidusuario= validUsuario();
		invalidusuario.setCedula(null);
		assertFalse(isValid(invalidusuario));
	}
	
	@Test
	public void BeanValidation_CedulaIsLessThanSize_NotValid()
	{
		Usuario invalidusuario=validUsuario();
		invalidusuario.setCedula("V123456");
		assertFalse(isValid(invalidusuario));
	}
	
	@Test
	public void BeanValidation_CedulaIsMoreThanSize_NotValid()
	{
		Usuario invalidusuario=validUsuario();
		invalidusuario.setCedula("V1234567890");
		assertFalse(isValid(invalidusuario));
	}
	
	@Test
	public void BeanValidation_LoginIsNull_NotValid()
	{
		Usuario invalidusuario= validUsuario();
		invalidusuario.setLogin(null);
		assertFalse(isValid(invalidusuario));
	}
	
	@Test
	public void BeanValidation_LoginIsLessThanSize_NotValid()
	{
		Usuario invalidusuario=validUsuario();
		invalidusuario.setLogin("abcde");
		assertFalse(isValid(invalidusuario));
	}
	
	@Test
	public void BeanValidation_LoginIsMoreThanSize_NotValid()
	{
		Usuario invalidusuario=validUsuario();
		invalidusuario.setLogin("abcdehijklmnopqrstuvwx");
		assertFalse(isValid(invalidusuario));
	}
	
	@Test
	public void BeanValidation_PasswordIsNull_NotValid()
	{
		Usuario invalidusuario= validUsuario();
		invalidusuario.setPassword(null);
		assertFalse(isValid(invalidusuario));
	}
	
	@Test
	public void BeanValidation_PasswordIsLessThanSize_NotValid()
	{
		Usuario invalidusuario=validUsuario();
		invalidusuario.setPassword("abcdefg");
		assertFalse(isValid(invalidusuario));
	}
	
	@Test
	public void BeanValidation_PasswordIsMoreThanSize_NotValid()
	{
		Usuario invalidusuario=validUsuario();
		invalidusuario.setPassword("abcdehijklmnopqrstuvwx");
		assertFalse(isValid(invalidusuario));
	}

}
