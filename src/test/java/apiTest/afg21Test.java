package apiTest;
import java.util.List;
import java.util.Map;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import utils.TestBase;
import constants.DataConstant;
import static io.restassured.RestAssured.*;

 public class afg21Test extends TestBase{

    @Test
    public void testGetAfgReport() {

        Response resp = given().spec(reportsRequest())
                .body("{\"operationName\":\"GetAFGReportData\",\"variables\":{\"teacherId\":\""+DataConstant.Valid_Teacher_IDS+"\",\"organizationId\":\""+DataConstant.Valid_Org_ID+"\",\"subject\":"+DataConstant.Valid_Sub_IDS+",\"datesAtRisk\":10400,\"isGroupSelected\":true,\"studentIds\":null,\"groupIds\":[\"787bb240290f457cb3b5a6892cbf078a\",\"c9728f5ee2014cd9b61bd87ce61aec8a\",\"5f19ca24ccaf4961acfaf4e26ce95819\",\"1b12d846e3ba4106ac571561bb0cc5bb\",\"137f16bff0f149b3b05e3332c6087bdf\",\"0ca2e567cd2c4f7c8bdf9bf7fb7742c8\",\"a831384a8fec47068a8293599f74cbb2\",\"c20dac14715a4012ac1c44c0d9919596\",\"64477cb8850a483b8ebd031a016b0270\",\"8246e9c51d26450a9d28eb7798537b99\",\"614749d2d2fa45f486beeb56fa1fb070\",\"18b7f32cc13e4e48b1aae08ccde4b108\",\"3f314f764eda4a0ab283d881329cee3c\",\"4a066dc079cc4fd1831d357ca252d66b\"],\"assignmentIds\":[\"196637\",\"187720\",\"199222\",\"218356\",\"190611\",\"194253\",\"196596\",\"194641\",\"187695\",\"187724\",\"190065\",\"187700\",\"187723\",\"188073\",\"218046\",\"186517\",\"186628\",\"187025\",\"202967\",\"205303\",\"215618\",\"187236\",\"196753\",\"194703\",\"188403\",\"187153\",\"190607\"],\"orderBy\":\"STRAND\",\"hierarchy\":\"skills\"},\"query\":\"query GetAFGReportData($organizationId: String!, $teacherId: String!, $isGroupSelected: Boolean, $studentIds: [String!], $subject: Float!, $groupIds: [String!], $assignmentIds: [String!], $orderBy: String, $datesAtRisk: Float, $hierarchy: String) {\\n" + //
                                        "  getAFGReportData(\\n" + //
                                        "    filterParams: {subject: $subject, isGroupSelected: $isGroupSelected, studentIds: $studentIds, groupIds: $groupIds, assignmentIds: $assignmentIds, orderBy: $orderBy, datesAtRisk: $datesAtRisk, hierarchy: $hierarchy}\\n" + //
                                        "    userId: $teacherId\\n" + //
                                        "    organizationId: $organizationId\\n" + //
                                        "  ) {\\n" + //
                                        "    reportRun\\n" + //
                                        "    assignmentRow {\\n" + //
                                        "      organizationName\\n" + //
                                        "      teacherTitle\\n" + //
                                        "      teacherFirstName\\n" + //
                                        "      teacherLastName\\n" + //
                                        "      teacherName\\n" + //
                                        "      teacherUsername\\n" + //
                                        "      teacherID\\n" + //
                                        "      gradeDisplayOrder\\n" + //
                                        "      assignmentTitle\\n" + //
                                        "      assignmentID\\n" + //
                                        "      strandSkillRows {\\n" + //
                                        "        strandName\\n" + //
                                        "        strandLevel\\n" + //
                                        "        catalogNum\\n" + //
                                        "        objectiveID\\n" + //
                                        "        loDescription\\n" + //
                                        "        lessonNumber\\n" + //
                                        "        lessonTitle\\n" + //
                                        "        targetedLessonLink\\n" + //
                                        "        studentRows {\\n" + //
                                        "          username\\n" + //
                                        "          studentName\\n" + //
                                        "          studentFirstName\\n" + //
                                        "          studentLastName\\n" + //
                                        "          studentId\\n" + //
                                        "          personID\\n" + //
                                        "          failedDate\\n" + //
                                        "          grade\\n" + //
                                        "          group {\\n" + //
                                        "            groupId\\n" + //
                                        "            groupName\\n" + //
                                        "            __typename\\n" + //
                                        "          }\\n" + //
                                        "          __typename\\n" + //
                                        "        }\\n" + //
                                        "        __typename\\n" + //
                                        "      }\\n" + //
                                        "      __typename\\n" + //
                                        "    }\\n" + //
                                        "    __typename\\n" + //
                                        "  }\\n" + //
                                        "}\\n" + //
                                        "\"}")
                .when()
                .post("graphql")
                .then()
                .extract().response();
        // System.out.println("Response Status Code: " + resp.asString());
        // resp.prettyPrint();
        // resp.getBody().asString();

        List<Map<String, Object>> productMap = resp.jsonPath().getList("data.getAFGReportData.assignmentRow");
                for (Map<String, Object> product : productMap) {
                        // System.out.println("Product: " + product);
                        String organizationName = (String) product.get("organizationName");
                        String teacherName = (String) product.get("teacherName");
                        String assignmentID = (String)product.get("assignmentID");
                        System.out.println("organizationName: " + organizationName + ", teacherName: " + teacherName + ", ID: " + assignmentID);
                }
    }

    @DataProvider(name = "afgDataProvider")
    public Object[][] getData() {
        return new Object[][] {
                { "TC01", "Verify the status code 200 for response body for providing only correct teacherIds in request body","Valid_Teacher_IDS" },
                { "TC01", "Verify the status code 200 for response body for providing only correct teacherIds in request body","Valid_Teacher_IDS" },
                { "TC01", "Verify the status code 200 for response body for providing only correct teacherIds in request body","Valid_Teacher_IDS" },
                { "TC01", "Verify the status code 200 for response body for providing only correct teacherIds in request body","Valid_Teacher_IDS" },
                { "TC01", "Verify the status code 200 for response body for providing only correct teacherIds in request body","Valid_Teacher_IDS" },
                { "TC01", "Verify the status code 200 for response body for providing only correct teacherIds in request body","Valid_Teacher_IDS" },
                { "TC01", "Verify the status code 200 for response body for providing only correct teacherIds in request body","Valid_Teacher_IDS" },
                { "TC01", "Verify the status code 200 for response body for providing only correct teacherIds in request body","Valid_Teacher_IDS" },
                
        
        };
    }
}
 

