package it.project.configurate;

import com.google.gson.Gson;
import it.project.dto.FileDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class HttpServletHelper {

    public static String getBody(HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("UTF-8");
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = request.getReader()) {
            char[] charBuffer = new char[128];
            int bytesRead;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        }

        return stringBuilder.toString();
    }

    public static <T> T convertStringIntoObject(HttpServletRequest req, Class<T> clazz) throws IOException {
        String body = getBody(req);

        System.out.println("===>>> body: " + body);

        T objectDTO = new Gson().fromJson(body, clazz);
        return objectDTO;
    }

    public static <T> T convertStringIntoObject(String str, Class<T> clazz) throws IOException {
        T objectDTO = new Gson().fromJson(str, clazz);
        return objectDTO;
    }
}


