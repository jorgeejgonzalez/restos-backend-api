package org.idisoft.restos.model.jpa;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.idisoft.restos.model.EstatusRegistro;
import org.idisoft.restos.model.Registro;
import org.idisoft.restos.model.TipoUsuario;
import org.idisoft.restos.model.Usuario;

@SuppressWarnings("serial")
@Entity
@Table(
		name = ConstantesORM.USUARIO_TABLE_NAME,
		uniqueConstraints=
		{
				@UniqueConstraint(columnNames=ConstantesORM.USUARIO_LOGIN_ATTRIBUTE_NAME),
				@UniqueConstraint(columnNames=ConstantesORM.USUARIO_EMAIL_ATTRIBUTE_NAME)
		}
)
public class UsuarioEntity implements Usuario, Registro, Serializable {
	
	@Column(name=ConstantesORM.USUARIO_CEDULA_COLUMN_NAME)
	@Id
	@NotNull
	@Pattern(regexp=ConstantesBeanValidation.USUARIO_CEDULA_REGEXP)
	@Size(min=ConstantesBeanValidation.USUARIO_CEDULA_MIN_SIZE,
			max=ConstantesBeanValidation.USUARIO_CEDULA_MAX_SIZE)
	private String cedula;
	
	@Column(name=ConstantesORM.USUARIO_LOGIN_COLUMN_NAME)
	@NotNull
	@Pattern(regexp=ConstantesBeanValidation.USUARIO_LOGIN_REGEXP)
	@Size(min=ConstantesBeanValidation.USUARIO_LOGIN_MIN_SIZE,
			max=ConstantesBeanValidation.USUARIO_LOGIN_MAX_SIZE)
	private String login;
	
	@Column(name=ConstantesORM.USUARIO_PASSWORD_COLUMN_NAME)
	@NotNull
	@Size(min=ConstantesBeanValidation.USUARIO_PASSWORD_MIN_SIZE,
			max=ConstantesBeanValidation.USUARIO_PASSWORD_MAX_SIZE)
	private String password;
	
	@Column(name=ConstantesORM.USUARIO_EMAIL_COLUMN_NAME)
	@NotNull
	@Email
	private String email;
	
	@Column(name=ConstantesORM.USUARIO_TIPO_COLUMN_NAME)
	@NotNull
	private TipoUsuario tipo;
	
	@Column(name=ConstantesORM.USUARIO_NOMBRE_COLUMN_NAME)
	@NotNull
	@Pattern(regexp=ConstantesBeanValidation.USUARIO_NOMBRE_REGEXP)
	@Size(min=ConstantesBeanValidation.USUARIO_NOMBRE_MIN_SIZE,
			max=ConstantesBeanValidation.USUARIO_NOMBRE_MAX_SIZE)
	private String nombre;
	
	@Column(name=ConstantesORM.USUARIO_APELLIDO_COLUMN_NAME)
	@NotNull
	@Pattern(regexp=ConstantesBeanValidation.USUARIO_APELLIDO_REGEXP)
	@Size(min=ConstantesBeanValidation.USUARIO_APELLIDO_MIN_SIZE,
			max=ConstantesBeanValidation.USUARIO_APELLIDO_MAX_SIZE)
	private String apellido;
	
	@Column(name=ConstantesORM.USUARIO_DIRECCION_COLUMN_NAME)
	@NotNull
	@Size(max=ConstantesBeanValidation.USUARIO_DIRECCION_MAX_SIZE)
	private String direccion;
	
	@Column(name=ConstantesORM.USUARIO_TELEFONO_COLUMN_NAME)
	@NotNull
	@Pattern(regexp=ConstantesBeanValidation.USUARIO_TELEFONO_REGEXP)
	@Size(min=ConstantesBeanValidation.USUARIO_TELEFONO_MIN_SIZE, 
			max=ConstantesBeanValidation.USUARIO_TELEFONO_MAX_SIZE)
	private String telefono;
	
	@Column(name=ConstantesORM.USUARIO_ESTATUSREGISTRO_COLUMN_NAME)
	@NotNull
	private EstatusRegistro estatusregistro;

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
	public void setCedula(final String cedula) {
		this.cedula=cedula;
	}
	
	@Override
	public void setLogin(final String login) {
		this.login=login;
	}

	@Override
	public void setPassword(final String password) {
		this.password=password;
	}
	
	@Override
	public String getNombre()
	{
		return nombre;
	}
	
	@Override
	public void setNombre(final String nombre)
	{
		this.nombre=nombre;
	}

	@Override
	public String getApellido() {
		return apellido;
	}

	@Override
	public void setApellido(final String apellido) {
		this.apellido=apellido;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(final String email) {
		this.email=email;
	}

	@Override
	public TipoUsuario getTipo() {
		return tipo;
	}

	@Override
	public void setTipo(final TipoUsuario tipo) {
		this.tipo=tipo;
	}

	@Override
	public String getDireccion() {
		return direccion;
	}

	@Override
	public void setDireccion(final String direccion) {
		this.direccion=direccion;
	}

	@Override
	public String getTelefono() {
		return telefono;
	}

	@Override
	public void setTelefono(final String telefono) {
		this.telefono=telefono;
	}

	@Override
	public EstatusRegistro getEstatusRegistro() {
		return estatusregistro;
	}

	@Override
	public void setEstatusRegistro(final EstatusRegistro estatusregistro) {
		this.estatusregistro=estatusregistro;
	}
	
	@Override
	public boolean equals(Object o)
	{
		boolean check=false;
		if(o instanceof UsuarioEntity)
		{
			UsuarioEntity usuariocheck=(UsuarioEntity) o;
			check=cedula.equals(usuariocheck.getCedula())
					&& login.equals(usuariocheck.getLogin())
					&& password.equals(usuariocheck.getPassword())
					&& email.equals(usuariocheck.getEmail())
					&& nombre.equals(usuariocheck.getNombre())
					&& apellido.equals(usuariocheck.getApellido())
					&& direccion.equals(usuariocheck.getDireccion())
					&& telefono.equals(usuariocheck.getTelefono());
		}
		return check;
	}
	
	@Override
	public int hashCode()
	{
		int hash=Objects.hash(cedula,login,
				password,email,
				nombre,apellido,
				direccion,telefono);
		return hash;
	}
}
