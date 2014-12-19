package org.idisoft.restos.model.jpa;

import java.util.Set;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Dependent
public class BeanValidator<T> {
	
	private ValidatorFactory validatorfactory;
	private Set<ConstraintViolation<T>> violations;
	
	public BeanValidator()
	{
		this.validatorfactory= Validation.buildDefaultValidatorFactory();
	}
	
	@Inject
	public BeanValidator(ValidatorFactory validatorfactory)
	{
		this.validatorfactory=validatorfactory;
	}
	
	public void validate(T entity)
	{
		Validator validator=validatorfactory.getValidator();
		violations=validator.validate(entity);
	}
	
	public boolean isValid()
	{
		if(violations==null)
			return false;
		
		return violations.isEmpty();
	}
	

}
