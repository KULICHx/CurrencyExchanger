package utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.InputStream;
import java.util.Scanner;

public class Utils {
    public static String getStringFromPartName(HttpServletRequest request, String partName) {
        try {
            Part part = request.getPart(partName);
            return getStringFormInputStream(part.getInputStream());
        } catch (Exception e) {
            return null;
        }
    }

    public static String getStringFormInputStream(InputStream inputStream) {
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
