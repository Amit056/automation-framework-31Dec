package utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CommonUtils {

    /**
     * 
     * to get file as string
     */

    public static String convertFileToString(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String str;
        StringBuffer sb = new StringBuffer();
        while ((str = in.readLine()) != null) {

            sb.append(str + " ");
        }
        in.close();
        return sb.toString();

    }

}