package split.demo.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import split.demo.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("ContentType", "application/json");
        HelloData data = new HelloData();
        data.setUsername("hi");
        data.setAge(30);
        String result = objectMapper.writeValueAsString(data);
        resp.getWriter().write(result);
    }
}
