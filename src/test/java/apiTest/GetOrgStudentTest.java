package apiTest;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.TestBase;

public class GetOrgStudentTest extends TestBase {

     @Test(groups = {"smoke"})
    public void GetAllOrgStudent() {

        Response resp = RestAssured
                .given().spec(teacherRequest())
                .body("{\"operationName\":\"GetOrgStudent\",\"variables\":{\"groupId\":\"\"},\"query\":\"query GetOrgStudent($groupId: String) {\\n" + //
                                        "  getOrgStudent(groupId: $groupId) {\\n" + //
                                        "    success\\n" + //
                                        "    message\\n" + //
                                        "    data {\\n" + //
                                        "      firstName\\n" + //
                                        "      middleName\\n" + //
                                        "      lastName\\n" + //
                                        "      studentId\\n" + //
                                        "      username\\n" + //
                                        "      userId\\n" + //
                                        "      grade {\\n" + //
                                        "        gradeId\\n" + //
                                        "        gradeName\\n" + //
                                        "        __typename\\n" + //
                                        "      }\\n" + //
                                        "      __typename\\n" + //
                                        "    }\\n" + //
                                        "    __typename\\n" + //
                                        "  }\\n" + //
                                        "}\\n" + //
                                        "\"}")
                .post("graphql")
                .then()
                .extract().response();
        resp.prettyPrint();

        /**
         * Read data from json response as list
         */

        List<Map<String, Object>> assignments = resp.jsonPath().getList("data.getOrgStudent.data");
        for (Map<String, Object> assignment : assignments) {
            String userId = (String) assignment.get("userId");
            String username = (String) assignment.get("username");
            String firstName = (String) assignment.get("firstName");
            System.out.println("userId: " + userId + ", firstName: " +
                    username + " ,username: " + firstName);

            /***
             * 
             * Read data from json response as map
             */
            // Map<String, Object> rootMap = resp.jsonPath().getMap("$"); // "$" represents the root of the JSON
            // Map<String, Object> dataMap = (Map<String, Object>) rootMap.get("data");
            // Map<String, Object> getGroupsMap = (Map<String, Object>) dataMap.get("getGroups");
            // List<Map<String, Object>> productMapData = (List<Map<String, Object>>) getGroupsMap.get("data");

            // // Accessing some fields from the map
            // for (Map<String, Object> product : productMapData) {
            //     String groupId1 = (String) product.get("groupId");
            //     String groupOwnerId1 = (String) product.get("groupOwnerId");
            //     String groupName1 = (String) product.get("groupName");
            //     System.out.println("groupId: " + groupId1 + ", groupOwnerId: " +
            //             groupOwnerId1 + " ,groupName: " + groupName1);
            // }

        }
    }
    
}
