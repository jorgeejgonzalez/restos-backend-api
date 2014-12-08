package org.idisoft.restos.model.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.idisoft.restos.model.Usuario;

@SuppressWarnings("serial")
@Entity
@Table(
		name = ConstantesORM.USUARIO_TABLE_NAME,
		uniqueConstraints=
		{
				@UniqueConstraint(columnNames="email"),
				@UniqueConstraint(columnNames="login")
		}
)
public class UsuarioJPA implements Usuario, Serializable {
	
	@Column(name=ConstantesORM.USUARIO_CEDULA_COLUMN_NAME)
	@Id
	@NotNull
	@Pattern(regexp=ConstantesBeanValidation.USUARIO_CEDULA_REGEXP)
	@Size(min=ConstantesBeanValidation.USUARIO_CEDULA_MIN_SIZE,
			max=ConstantesBeanValidation.USUARIO_CEDULA_MAX_SIZE)
	private String cedula;
	
	@Column(name=ConstantesORM.USUARIO_LOGIN_COLUMN_NAME)
	@NotNull
	@Size(min=ConstantesBeanValidation.USUARIO_LOGIN_MIN_SIZE,
			max=ConstantesBeanValidation.USUARIO_LOGIN_MAX_SIZE)
	private String login;
	
	@Column(name=ConstantesORM.USUARIO_PASSWORD_COLUMN_NAME)
	@NotNull
	@Size(min=ConstantesBeanValidation.USUARIO_PASSWORD_MIN_SIZE,
			max=ConstantesBeanValidation.USUARIO_PASSWORD_MAX_SIZE)
	private String password;

	@Override
	public String getCedula() {
		return cedula;
	}

	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setCedula(String cedula) {
		this.cedula=cedula;
	}

	@Override
	public void setLogin(String login) {
		this.login=login;
	}

	@Override
	public void setPassword(String password) {
		this.password=password;
	}

}
