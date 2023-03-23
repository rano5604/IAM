package regressionTest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.util.Base64;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ServiceTestRegression {

	String token;

	@BeforeClass
	public void LoginTest() {
		

        String clientId = "fqtz8UnL2sFfKcVZGgoMvzf3kmWEdfkE";
        String clientSecret = "MZcgtYtOOxigj02Q2CAIZeHodACRT825OdLZGDCqAruPY2MJxH6dS4xreVcGiedX";

        
        String username = "sunny@yopmail.com";
        String password = "123456";

        String scope = "iam_user admin";
        
        String authHeader = "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
        
		String requestBody = String.format("grant_type=password&username=%s&password=%s&scope=%s", username, password, scope);
		
		baseURI="http://tiger-iam.com/iam/oauth2/token";
		Response response = given()
				.header("Authorization",authHeader)
				.header("Content-Type", "application/x-www-form-urlencoded")
				.body(requestBody)
				.post();
		token =response.jsonPath().get("access_token");
		//System.out.println(response.asPrettyString());
		System.out.println(token);

	}
	
	
	@Test
	public void getAPI() {
		baseURI="http://tiger-iam.com";
		Response response = given()
				.header("Authorization", "Bearer "+token)
				.formParam("startFrom", "0")
				.formParam("maxCount", "10")
				.get("/iam/admin/apis")
				.then()
				.extract()
				.response();
		assertEquals(200,response.getStatusCode());
	}

}
