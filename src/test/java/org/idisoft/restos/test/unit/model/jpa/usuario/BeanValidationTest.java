package org.idisoft.restos.test.unit.model.jpa.usuario;

import static org.junit.Assert.*;

import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.BeanValidator;
import org.idisoft.restos.test.util.TestEntitiesFactory;
import org.junit.Test;

public class BeanValidationTest {
	
	private boolean isValid(Usuario usuario)
	{
		BeanValidator<Usuario> beanvalidator=new BeanValidator<Usuario>();
		beanvalidator.validate(usuario);
		return beanvalidator.isValid();
	}
	
	private Usuario validUsuario()
	{
		return TestEntitiesFactory.validUsuario();
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
	public void BeanValidation_LoginWithNumbers_Valid()
	{
		Usuario validusuario=validUsuario();
		validusuario.setLogin("abc123");
		assertTrue(isValid(validusuario));
	}
	
	@Test
	public void BeanValidation_LoginWithUnderscore_Valid()
	{
		Usuario validusuario=validUsuario();
		validusuario.setLogin("abc_123");
		assertTrue(isValid(validusuario));
	}
	
	@Test
	public void BeanValidation_LoginWithDash_Valid()
	{
		Usuario validusuario=validUsuario();
		validusuario.setLogin("abc-123");
		assertTrue(isValid(validusuario));
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
	
	@Test
	public void BeanValidation_NombreIsLessThanMinSize_NotValid()
	{
		Usuario invalidusuario=validUsuario();
		invalidusuario.setNombre("ab");
		assertFalse(isValid(invalidusuario));
	}
	
	@Test
	public void BeanValidation_NombreIsMoreThanMaxSize_NotValid()
	{
		Usuario invalidusuario=validUsuario();
		invalidusuario.setNombre("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz");
		assertFalse(isValid(invalidusuario));
	}
	
	@Test
	public void BeanValidation_NombreIsNull_NotValid()
	{
		Usuario invalidusuario=validUsuario();
		invalidusuario.setNombre(null);
		assertFalse(isValid(invalidusuario));
	}
	
	@Test
	public void BeanValidation_NombreHasNumbers_NotValid()
	{
		Usuario invalidusuario=validUsuario();
		invalidusuario.setNombre("name1");
		assertFalse(isValid(invalidusuario));
	}
	
	@Test
	public void BeanValidation_NombreHasEmptySpaces_Valid()
	{
		Usuario validusuario=validUsuario();
		validusuario.setNombre("name name");
		assertTrue(isValid(validusuario));
	}

}
