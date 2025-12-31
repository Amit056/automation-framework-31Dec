package dbTest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.annotations.Test;

import utils.DBUtils;

public class ReadDataFromDb {
    @Test

    public List<String> testReadData() {
        String sql = "Select id, price from products where price>100 "; // Replace 'users' with your table name
        DBUtils  dbUtil=new DBUtils();
        //  dbUtil.executeQuery(sql);

        List<Map<String, Object>> dbData = dbUtil.executeQuery(sql);

        List<String> dbResult = dbData.stream()
        .map(row -> "ID=" + row.get("id") + ", Price=" + row.get("price"))
        .collect(Collectors.toList());


// for (Map<String, Object> row : dbData) {
    
//     System.out.println(row);
// }
// for (String record : dbResult) {
//     System.out.println(record); 
        
//     }
System.out.println(dbResult);
        return dbResult;
}}
