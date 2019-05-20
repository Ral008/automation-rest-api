package com.pe.automation.steps;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UserSteps {

	private String ENDPOINT_PUBLIC_USER = "https://api.octoperf.com/public/users";
	private String PATH;

	@Step
	public void basePathUsuario(String basePath) {
		PATH = basePath;
	}

	@Step
	public void ingresarCredenciales(String user, String password) {
		SerenityRest.given().contentType("application/json")
					.queryParam("username", user)
					.queryParam("password", password)
		            .baseUri(ENDPOINT_PUBLIC_USER)
		            .log().all();
	}

	@Step
	public void realizarPeticion() {
		SerenityRest.when().post(PATH);
	}

	@Step
	public int obtenerCodigoDelEstado() {
		return SerenityRest.then().extract().statusCode();
	}

	@Step
	public JsonPath obtenerResponseBody() {
		return SerenityRest.then().extract().body().jsonPath(); 
	}

}
