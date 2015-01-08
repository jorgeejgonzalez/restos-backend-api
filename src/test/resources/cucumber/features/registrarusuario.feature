Feature: La aplicacion debe registrar usuarios nuevos

Scenario: Registro de Usuario nuevo 

	Given I want to register a new Usuario
	When I invoke the registerUsuario service
	Then the webservice should respond with HTTP Status OK

Scenario: Registro de Usuario con datos ya registrados
	
	When I invoke the registerUsuario service
	And the cedula has already been used
	Or the login has already been used
	Or the email has already been used
	Then the webservice should respond with HTTP Status Conflict
	
Scenario: Registro de Usuario con datos que no pasan la validacion 
	
	When I invoke the registerUsuario service
	And the Usuario data is invalid
	Then the webservice should respond with HTTP Status Not_Acceptable