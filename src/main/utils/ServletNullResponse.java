package utils;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class ServletNullResponse {
    public static void nullResponse(HttpServletResponse resp, PrintWriter out) throws IOException {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "IllegalArgumentException");
        out.print(resp);
        out.flush();
    }
}
