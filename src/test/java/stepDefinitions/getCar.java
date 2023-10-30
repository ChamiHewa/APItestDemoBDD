package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class getCar {

    @DataProvider(name = "cars")
    public static Object[][] cars() {
        return new Object[][] {{"Saloon", 200,"HTTP/1.1 200 OK"},{"SUV", 200,"HTTP/1.1 200 OK"},{"Hatchback", 200,"HTTP/1.1 200 OK"},{"Sedan", 404,"HTTP/1.1 404 Not Found"},{" ", 404,"HTTP/1.1 404 Not Found"}};
    }

    @Test(dataProvider = "cars")
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
