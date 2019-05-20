Feature: Validacion de acceso
  Como usuario
  Quiero acceder al servicio de usuarios para obtener el token
  
  Background:
    Given el siguiente path "/login"
  
  Scenario: Hacer login con usuario valido y password correcto  
    And tengo el usuario "laboratory.testing911@gmail.com" y password "Peru$2018"
    When se realiza una solicitud con las credenciales
    Then deberia obtener el codigo de respuesta "200"
    And el response body contiene un valor para el key token
    
  Scenario: Hacer login con usuario valido y password incorrecto  
    And tengo el usuario "laboratory.testing911@gmail.com" y password "incorrecto"
    When se realiza una solicitud con las credenciales
    Then deberia obtener el codigo de respuesta "404"
    And validar el contenido del response body  