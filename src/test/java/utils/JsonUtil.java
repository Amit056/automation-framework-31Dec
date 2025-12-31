package utils;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtil {

    // ✅ THIS WAS MISSING
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String readJson(String fileName) {
        // Correcting the resource path to extract the URL
        URL resourceUrl = JsonUtil.class.getClassLoader().getResource("payload/" + fileName);
        if (resourceUrl == null) {
            throw new RuntimeException("File not found: payload/" + fileName);
        }

        System.out.println(resourceUrl); // Print URL if needed for debugging

        try (InputStream is = resourceUrl.openStream()) { // Use URL to open InputStream
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Unable to read json file: " + fileName, e);
        }
    }

    /**
     * propertyPath example:
     * variables.teacherId
     * variables.subject
     * variables.groupIds
     */
    public static String setProperty(String json, String propertyPath, Object value) {
        try {
            JsonNode rootNode = mapper.readTree(json);

            // ✅ FIX 3: correct regex
            String[] path = propertyPath.split("\\.");

            ObjectNode current = (ObjectNode) rootNode;

            for (int i = 0; i < path.length - 1; i++) {
                current = (ObjectNode) current.get(path[i]);
            }

            // ✅ FIX 2: use mapper.valueToTree
            current.set(path[path.length - 1], mapper.valueToTree(value));

            return mapper.writeValueAsString(rootNode);

        } catch (Exception e) {
            throw new RuntimeException("Failed to update json property: " + propertyPath, e);
        }
    }
}