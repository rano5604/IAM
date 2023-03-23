package regressionTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.Base64;

public class TokenRequest {
    public static void main(String[] args) {
        // Set the token endpoint URL
        String tokenUrl = "http://tiger-iam.com/iam/oauth2/token";

        // Set the client ID and secret
        String clientId = "fqtz8UnL2sFfKcVZGgoMvzf3kmWEdfkE";
        String clientSecret = "MZcgtYtOOxigj02Q2CAIZeHodACRT825OdLZGDCqAruPY2MJxH6dS4xreVcGiedX";

        // Set the username and password
        String username = "sunny@yopmail.com";
        String password = "123456";

        // Set the scope
        String scope = "iam_user admin";

        // Set the authentication headers
        String authHeader = "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());

        // Set the request parameters
        String requestBody = String.format("grant_type=password&username=%s&password=%s&scope=%s", username, password, scope);

        // Send the token request
        RestAssured.baseURI = tokenUrl;

       Response res= given()
            .header("Authorization", authHeader)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .body(requestBody)
            .post();

       
       System.out.println(res.asPrettyString());
    }
}