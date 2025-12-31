package apiTest;

import static io.restassured.RestAssured.*;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;
import constants.Assignments;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import utils.ConfigManager;
import utils.TestBase;

public class AssignmentsDetailTest extends TestBase {

        @Test
        @Story("Get assigned assignments")
        @Severity(SeverityLevel.CRITICAL)
        @Description("Verify assigned assignments API returns 200")
        public void testMasteryPerformanceDetails() {
                Response resp = given().spec(reportsRequest())
                                .body(Assignments.assignmentsParameterized(
                                                String.valueOf(ConfigManager.getInt("subId")),
                                                ConfigManager.get("userId"), ConfigManager.get("orgId")))
                                .when()
                                .post("graphql").then()
                                .extract().response();
                // System.out.println("Response Status Code: " + resp.asString());
                // resp.prettyPrint();
                // resp.getBody().asString();
                List<Map<String, Object>> productMap = resp.jsonPath().getList("data.assignments");
                for (Map<String, Object> product : productMap) {
                        // System.out.println("Product: " + product);
                        String subId = (String) product.get("subjectId");
                        String assignmentId = (String) product.get("assignmentId");
                        String assignmentAssignerFirstName = (String) product.get("assignmentAssignerFirstName");
                        String assignmentType = (String) product.get("assignmentType");
                        System.out.println("subId: " + subId + ", assignmentId: " + assignmentId + ", Title: "
                                        + assignmentAssignerFirstName + ", Price: " + assignmentType);
                }
        }
}
