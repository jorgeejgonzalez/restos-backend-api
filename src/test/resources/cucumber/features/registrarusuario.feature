Feature: Registrar Usuarios

Scenario: Registro de Usuario Nuevo

	Given I want to register a new Usuario
	When I invoke the registerUsuario service
	Then the webservice should respond with HTTP Status OK
	
	When I invoke the registerUsuario service
	And the cedula has already been used
	Or the login has already been used
	Or the email has already been used
	Then the webservice should respond with HTTP Status Conflict
	
	When I invoke the registerUsuario service
	And the Usuario data is invalid
	Then the webservice should respond with HTTP Status Not_Acceptable