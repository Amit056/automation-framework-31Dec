package apiTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ConfigManager;
import utils.TestBase;

public class GET_USAGEREPORTSTest extends TestBase {

        @Test
        public void getAssignedAssignment() {
                HashMap<String, Object> payload = new HashMap<>();
                payload.put("endpoint", "GET_USAGEREPORTS");
                payload.put("httpMethod", "POST");
                payload.put("params", Map.of(
                                "orgId", ConfigManager.get("orgId"),
                                "staffId", ConfigManager.get("userId")));
                payload.put("requestBody", Map.of(
                                "assignmentIds", new int[] {}));

                Response resp = RestAssured
                                .given().spec(teacherRequest())
                                .body(payload)
                                .when()
                                .post("home")
                                .then()
                                .extract().response();

                resp.prettyPrint();

                String respStatusCode = Optional.ofNullable(resp).isPresent() ? String.valueOf(resp.getStatusCode())
                                : resp.asString();
                System.out.println("Response Status Code  ========: " + respStatusCode);

        }
}
