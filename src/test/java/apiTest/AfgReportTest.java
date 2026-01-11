package apiTest;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ConfigManager;
import utils.JsonUtil;
import utils.TestBase;

public class AfgReportTest extends TestBase {
        String userId = ConfigManager.get("userId");
        String orgId = ConfigManager.get("orgId");
        int subjectM_R = ConfigManager.getInt("subId");
        float datesAtRisk = ConfigManager.getFloat("subId");
        boolean isGroupSelected = ConfigManager.getBoolean("isGroupSelected");
        List<String> studentIds = List.of();
        List<String> groupIds = List.of(
                        "787bb240290f457cb3b5a6892cbf078a", "c9728f5ee2014cd9b61bd87ce61aec8a",
                        "5f19ca24ccaf4961acfaf4e26ce95819", "1b12d846e3ba4106ac571561bb0cc5bb",
                        "137f16bff0f149b3b05e3332c6087bdf", "0ca2e567cd2c4f7c8bdf9bf7fb7742c8",
                        "a831384a8fec47068a8293599f74cbb2", "c20dac14715a4012ac1c44c0d9919596",
                        "64477cb8850a483b8ebd031a016b0270", "8246e9c51d26450a9d28eb7798537b99",
                        "614749d2d2fa45f486beeb56fa1fb070", "18b7f32cc13e4e48b1aae08ccde4b108",
                        "3f314f764eda4a0ab283d881329cee3c", "4a066dc079cc4fd1831d357ca252d66b");
        List<String> assignmentIds = List.of(
                        "196637", "187720", "199222", "218356", "190611", "194253", "196596", "194641", "187695",
                        "187724", "190065", "187700", "187723", "188073", "218046", "186517", "186628", "187025",
                        "202967", "205303", "215618", "187236", "196753", "194703", "188403", "187153", "190607");

        @Test(groups = {"smoke"})
        public void testGetAfgReport() {
                String payload = getResponseBody(userId, orgId, subjectM_R, isGroupSelected, groupIds, studentIds,
                                assignmentIds);

                Response resp = RestAssured
                                .given().spec(reportsRequest())
                                .body(payload)
                                .when()
                                .post("graphql")
                                .then()
                                .extract().response();
                // System.out.println("Response Status Code: " + resp.asString());
                // resp.prettyPrint();
                // resp.getBody().asString();

                // int actSC = resp.getStatusCode();
                // System.out.println(actSC);

                List<Map<String, Object>> productMap = resp.jsonPath().getList("data.getAFGReportData.assignmentRow");
                for (Map<String, Object> product : productMap) {
                        // System.out.println("Product: " + product);
                        String organizationName = (String) product.get("organizationName");
                        String teacherName = (String) product.get("teacherName");
                        String assignmentID = (String) product.get("assignmentID");
                        System.out.println("organizationName: " + organizationName + ", teacherName: " + teacherName
                                        + ", ID: " + assignmentID);
                }
        }

        public String getResponseBody(String userId, String orgId, int subject, boolean isGroupSelected,
                        List<String> groupIds, List<String> studentIds, List<String> assignmentIds) {
                String body = JsonUtil.readJson("afgReport.json");
                body = JsonUtil.setProperty(body, "variables.teacherId", userId);
                body = JsonUtil.setProperty(body, "variables.organizationId", orgId);
                body = JsonUtil.setProperty(body, "variables.subject", subject);
                body = JsonUtil.setProperty(body, "variables.isGroupSelected", isGroupSelected);
                body = JsonUtil.setProperty(body, "variables.groupIds", groupIds);
                body = JsonUtil.setProperty(body, "variables.studentIds", studentIds);
                body = JsonUtil.setProperty(body, "variables.assignmentIds", assignmentIds);

                return body;
        }

}
