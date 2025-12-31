package apiTest;
import java.util.List;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ConfigManager;
import utils.JsonUtil;
import utils.TestBase;

public class LSRReportTest extends TestBase {
        String userId = ConfigManager.get("userId");
        String orgId = ConfigManager.get("orgId");
        int subjectM_R = ConfigManager.getInt("subId");
        boolean isGroupSelected = true;
        List<String> studentIds = List.of(
                        "ffffffff693a58bdca2657f427ad59c5",
                        "ffffffff67f9feeb6210d132fc54bb2a",
                        "ffffffff67da5d1fd847230e60b11060",
                        "ffffffff67fe6b646210d132fc55727a",
                        "ffffffff682eec1d56eb4d29e55fd5d9",
                        "ffffffff63c195b44f47d35f7693f6f4",
                        "ffffffff63c195aa4f47d35f7693f6f3",
                        "ffffffff63c195c14f47d35f7693f6f5",
                        "ffffffff62c698a99906826d263613e7",
                        "ffffffff65e9da8457ed8e47875c4b90",
                        "ffffffff62d91657f8ee4d4c98916284",
                        "ffffffff62c69a0a9216a64034a905fb",
                        "ffffffff62c6a86208803c4bfa8eeeef",
                        "ffffffff68e4d34c2de32b5902b78524",
                        "ffffffff6875f1e2c3b37714c88f675b",
                        "ffffffff68d3eaa4f06e0b572826f93e",
                        "ffffffff68e4d3151b8dec60e0a5595c",
                        "ffffffff68c7e4185d8f6d758d3aac52",
                        "ffffffff68c7e87cae303570c33e7f84",
                        "ffffffff68d0e7496ec0481dfcf28cb6",
                        "ffffffff6866178d02789a5b4004d4e6",
                        "ffffffff693090485b26596192e5473d",
                        "ffffffff6874f58002789a5b400504f9",
                        "ffffffff68c2d08fc850e9538acd3766",
                        "ffffffff68c2d0add6d2e611450e68ed",
                        "ffffffff68d627279318455a2f5deff3",
                        "ffffffff687dd692c3b37714c88f7282",
                        "ffffffff687a52be8357c90cc22e92be",
                        "ffffffff68c92fae6d850234ee991b1c",
                        "ffffffff687a522bd9a8236280fe8307",
                        "ffffffff687dc619e1e0376add7b59a3",
                        "ffffffff693090796c09f50349991bfb");
        boolean lastSession = true;
        List<String> lastSessionDates = List.of();

        List<String> groupIds = List.of(
                        "787bb240290f457cb3b5a6892cbf078a",
                        "c9728f5ee2014cd9b61bd87ce61aec8a",
                        "5f19ca24ccaf4961acfaf4e26ce95819",
                        "1b12d846e3ba4106ac571561bb0cc5bb",
                        "137f16bff0f149b3b05e3332c6087bdf",
                        "0ca2e567cd2c4f7c8bdf9bf7fb7742c8",
                        "a831384a8fec47068a8293599f74cbb2",
                        "c20dac14715a4012ac1c44c0d9919596",
                        "64477cb8850a483b8ebd031a016b0270",
                        "8246e9c51d26450a9d28eb7798537b99",
                        "614749d2d2fa45f486beeb56fa1fb070",
                        "18b7f32cc13e4e48b1aae08ccde4b108",
                        "3f314f764eda4a0ab283d881329cee3c",
                        "4a066dc079cc4fd1831d357ca252d66b");
        List<Integer> assignmentIds = List.of(
                        196637,
                        187720,
                        201903,
                        199222,
                        194295,
                        194296,
                        218356,
                        190611,
                        194253,
                        196596,
                        194641,
                        187695,
                        187724,
                        190065,
                        187700,
                        187723,
                        188073,
                        218046,
                        186517,
                        186628,
                        187025,
                        202967,
                        203356,
                        205303,
                        215618,
                        187236,
                        196753,
                        188392,
                        189362,
                        194703,
                        188403,
                        187153,
                        188404,
                        190607);
        String additionalGrouping = "NONE";
        String lastDate = "2025-12-28T05:08:17.000Z";
        boolean prevLevel = false;

        @Test
        public void testGetLsrReport() {
               

                String payload = getResponseBody(userId, orgId, subjectM_R, isGroupSelected, groupIds, studentIds,
                                assignmentIds);

                Response resp = RestAssured
                                .given().spec(reportsRequest())
                                .body(payload)
                                .when()
                                .post("graphql")
                                .then()
                                .extract().response();
                System.out.println("Response Status Code: " + resp.getStatusCode());
                resp.prettyPrint();
                // resp.getBody().asString();

        }

        public String getResponseBody(String userId, String orgId, int subject, boolean isGroupSelected,
                        List<String> groupIds, List<String> studentIds, List<Integer> assignmentIds) {

                String body = JsonUtil.readJson("lsrReport.json");

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
