package constants;

public interface MasteryPerformanceDetails {
    String MASTERY_PERFORMANCE = 
    "{\"operationName\":\"MasteryPerformance\",\"variables\":{\"limit\":3,\"subjectId\":1},\"query\":\"query MasteryPerformance($assignmentIds: [Int!], $limit: Int, $standardId: Int, $subjectId: Int!) {\\n" + //
            "  masteryPerformanceDetails(\\n" + //
            "    subjectId: $subjectId\\n" + //
            "    assignmentIds: $assignmentIds\\n" + //
            "    standardId: $standardId\\n" + //
            "    limit: $limit\\n" + //
            "  ) {\\n" + //
            "    topPerformers {\\n" + //
            "      id\\n" + //
            "      title\\n" + //
            "      masteryPercent\\n" + //
            "      __typename\\n" + //
            "    }\\n" + //
            "    lowPerformers {\\n" + //
            "      id\\n" + //
            "      title\\n" + //
            "      masteryPercent\\n" + //
            "      __typename\\n" + //
            "    }\\n" + //
            "    studentIds\\n" + //
            "    __typename\\n" + //
            "  }\\n" + //
            "}\\n" + //
            "\"}";
        
}
    


