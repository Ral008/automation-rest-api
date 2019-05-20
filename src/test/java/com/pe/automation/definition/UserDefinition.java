package com.pe.automation.definition;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.pe.automation.steps.UserSteps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class UserDefinition {
	
	@Steps
	private UserSteps userSteps;
	
	@Given("^el siguiente path \"([^\"]*)\"$")
	public void el_siguiente_path(String basePath) {
		userSteps.basePathUsuario(basePath);
	}

	@Given("^tengo el usuario \"([^\"]*)\" y password \"([^\"]*)\"$")
	public void tengo_el_usuario_y_password(String user, String password) {
		userSteps.ingresarCredenciales(user, password);
	}

	@When("^se realiza una solicitud con las credenciales$")
	public void se_realiza_una_solicitud_con_las_credenciales() {
		userSteps.realizarPeticion();
	}

	@Then("^deberia obtener el codigo de respuesta \"([^\"]*)\"$")
	public void deberia_obtener_el_codigo_de_respuesta(String statusCode) {
		assertThat(String.valueOf(userSteps.obtenerCodigoDelEstado()), is(statusCode));
	}

	@Then("^el response body contiene un valor para el key token$")
	public void el_response_body_contiene_un_valor_para_el_key_token() {
		assertThat(userSteps.obtenerResponseBody(), notNullValue());
	}
	
	@Then("^validar el contenido del response body$")
	public void validar_el_contenido_del_response_body() {
	    assertThat(userSteps.obtenerResponseBody().getString("message"), is("The requested Item has not been found"));
	    assertThat(userSteps.obtenerResponseBody().getString("type"), is("ItemNotFoundException"));
	}

}
