package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class getCarsSteps {
    @Test
    @Parameters({"type", "code","statusLine"})
    public void VerifyCarTypeAgainstStatus(String type, int code, String statusLine) {

        // Given
        Response response = RestAssured.given()
                .baseUri("http://localhost:54356/")
                .when()
                .get("api/Cars/" + type)
                .then()
                .statusCode(code)
                .statusLine(statusLine)
                .log().all()
                .extract().response();

        // Check if the status code is 200
        if (response.getStatusCode() == 200) {
            // To verify booking count
            int responseCount = response.jsonPath().getList("cars").size();
            System.out.println("Responses count: " + responseCount);
        } else {
            System.out.println("Status code is not 200.");
        }
    }
}
