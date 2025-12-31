package constants;

public interface Assignments {

    String assignment="{\"operationName\":\"Assignments\",\"variables\":{\"subjectId\":\"1\",\"userId\":\"ffffffff62c3041317280f0030c45057\",\"orgId\":\"8a72026581a3e1df0181c9c7d10400a1\"},\"query\":\"query Assignments($subjectId: String!, $userId: String!, $orgId: String!) {\\n" + //
                "  assignments(subjectId: $subjectId, userId: $userId, orgId: $orgId) {\\n" + //
                "    assignmentId\\n" + //
                "    assignmentAssignerFirstName\\n" + //
                "    assignmentAssignerLastName\\n" + //
                "    assignmentName\\n" + //
                "    subjectId\\n" + //
                "    assignmentType\\n" + //
                "    __typename\\n" + //
                "  }\\n" + //
                "}\\n" + //
                "\"}";

                public static String assignmentsParameterized(String subjectId,String userId, String orgId ) {
                  String assignment="{\"operationName\":\"Assignments\",\"variables\":{\"subjectId\":\""+subjectId+"\",\"userId\":\""+userId+"\",\"orgId\":\""+orgId+"\"},\"query\":\"query Assignments($subjectId: String!, $userId: String!, $orgId: String!) {\\n" + //
                "  assignments(subjectId: $subjectId, userId: $userId, orgId: $orgId) {\\n" + //
                "    assignmentId\\n" + //
                "    assignmentAssignerFirstName\\n" + //
                "    assignmentAssignerLastName\\n" + //
                "    assignmentName\\n" + //
                "    subjectId\\n" + //
                "    assignmentType\\n" + //
                "    __typename\\n" + //
                "  }\\n" + //
                "}\\n" + //
                "\"}"; 

                    return assignment;
                }

                 String assignment1="{\"operationName\":\"Assignments\",\"variables\":{\"subjectId\":\"%$\",\"userId\":\"ffffffff62c3041317280f0030c45057\",\"orgId\":\"8a72026581a3e1df0181c9c7d10400a1\"},\"query\":\"query Assignments($subjectId: String!, $userId: String!, $orgId: String!) {\\n" + //
                "  assignments(subjectId: $subjectId, userId: $userId, orgId: $orgId) {\\n" + //
                "    assignmentId\\n" + //
                "    assignmentAssignerFirstName\\n" + //
                "    assignmentAssignerLastName\\n" + //
                "    assignmentName\\n" + //
                "    subjectId\\n" + //
                "    assignmentType\\n" + //
                "    __typename\\n" + //
                "  }\\n" + //
                "}\\n" + //
                "\"}";
}
