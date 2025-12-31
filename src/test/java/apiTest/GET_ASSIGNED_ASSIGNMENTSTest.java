package apiTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ConfigManager;
import utils.TestBase;

public class GET_ASSIGNED_ASSIGNMENTSTest extends TestBase {

        @Test
        public void getAssignedAssignment() {
                Map<String, Object> payload = new HashMap<>();
                payload.put("endpoint", "GET_ASSIGNED_ASSIGNMENTS");
                payload.put("httpMethod", "POST");
                payload.put("params", Map.of(
                                "orgId", ConfigManager.get("orgId"),
                                "userId", ConfigManager.get("userId")));
                payload.put("requestBody", Map.of(
                                "subjectId",ConfigManager.getInt("subId")));

                Response resp = RestAssured
                                .given().spec(teacherRequest())
                                .body(payload)
                                .post("home")
                                .then()
                                .extract().response();
                resp.prettyPrint();

                /**
                 * Read data from json response as list
                 */

                List<Map<String, Object>> assignments = resp.jsonPath().getList("data");
                for (Map<String, Object> assignment : assignments) {
                        String assignmentId = (String) assignment.get("assignmentId");
                        String subjectId = (String) assignment.get("subjectId");
                        String assignmentName = (String) assignment.get("assignmentName");
                        System.out.println("Assignment ID: " + assignmentId + ", subjectId: " +
                                        subjectId + " ,Assignment Name: " + assignmentName);
                }

                /**
                 * 
                 * Read data from json response as map
                 */

                Map<String, Object> productMap = resp.jsonPath().getMap("$"); // "$" represents the root of the JSON
                List<Map<String, Object>> productMapData = (List<Map<String, Object>>) productMap.get("data");

                for (Map<String, Object> product : productMapData) {
                        // System.out.println("Product: " + product);
                        String id = (String) product.get("assignmentId");
                        String assignmentAssignerFirstName = (String) product.get("assignmentAssignerFirstName");
                        String assignmentType = (String) product.get("assignmentType");
                        System.out.println("ID: " + id + ", Title: " + assignmentAssignerFirstName + ", Price: "
                                        + assignmentType);
                }
                Boolean success = (Boolean) productMap.get("success");
                List<Map<String, Object>> messages = (List<Map<String, Object>>) productMap.get("messages");
                System.out.println("Success: " + success + ", Messages: " + messages);
        }
}