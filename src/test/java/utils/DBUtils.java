package utils;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

    private static final String URL = "jdbc:mysql://localhost:3306/demo";
    private static final String USER = "root";
    private static final String PASSWORD = "Test@1234";

    public List<Map<String, Object>> executeQuery(String sql) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement stmt = conn.createStatement()) {

            System.out.println("Connected to the database successfully.");

            boolean isResultSet = stmt.execute(sql);

            if (isResultSet) {
                ResultSet rs = stmt.getResultSet();
                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();

                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();

                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = meta.getColumnLabel(i);
                        Object value = rs.getObject(i);
                        row.put(columnName, value);
                    }

                    resultList.add(row);
                }
            } else {
                System.out.println("Query OK, rows affected: " + stmt.getUpdateCount());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList; // return collected data
    }
}