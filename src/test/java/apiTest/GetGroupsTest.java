package apiTest;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.TestBase;

public class GetGroupsTest extends TestBase {
        @Test
        public void getAllGroups() {

                Response resp = RestAssured
                                .given().spec(teacherRequest())
                                .body("{\"operationName\":\"GetGroups\",\"variables\":{\"studentIds\":[]},\"query\":\"query GetGroups($studentIds: [String!]) {\\n"
                                                + //
                                                "  getGroups(studentIds: $studentIds) {\\n" + //
                                                "    data {\\n" + //
                                                "      groupId\\n" + //
                                                "      groupOwnerId\\n" + //
                                                "      groupName\\n" + //
                                                "      autoRostered\\n" + //
                                                "      studentIds\\n" + //
                                                "      googleOrLtia\\n" + //
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

                List<Map<String, Object>> assignments = resp.jsonPath().getList("data.getGroups.data");
                for (Map<String, Object> assignment : assignments) {
                        String groupId = (String) assignment.get("groupId");
                        String groupOwnerId = (String) assignment.get("groupOwnerId");
                        String groupName = (String) assignment.get("groupName");
                        System.out.println("groupId: " + groupId + ", groupOwnerId: " +
                                        groupOwnerId + " ,groupName: " + groupName);

                        /***
                         * 
                         * Read data from json response as map
                         */
                        Map<String, Object> rootMap = resp.jsonPath().getMap("$"); // "$" represents the root of the
                                                                                   // JSON
                        Map<String, Object> dataMap = (Map<String, Object>) rootMap.get("data");
                        Map<String, Object> getGroupsMap = (Map<String, Object>) dataMap.get("getGroups");
                        List<Map<String, Object>> productMapData = (List<Map<String, Object>>) getGroupsMap.get("data");

                        // Accessing some fields from the map
                        for (Map<String, Object> product : productMapData) {
                                String groupId1 = (String) product.get("groupId");
                                String groupOwnerId1 = (String) product.get("groupOwnerId");
                                String groupName1 = (String) product.get("groupName");
                                System.out.println("groupId: " + groupId1 + ", groupOwnerId: " +
                                                groupOwnerId1 + " ,groupName: " + groupName1);
                        }

                }
        }
}
