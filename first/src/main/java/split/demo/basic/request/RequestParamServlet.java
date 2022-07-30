package split.demo.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡRequest Parameterㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        printAll(req);
        //반드시 한개인 파라미터 조회
        System.out.println(req.getParameter("username"));
        //두개 이상인 파라미터 조회
        String[] usernames = req.getParameterValues("username");
        for (String username : usernames) System.out.println(username);
        resp.getWriter().write("success");
    }

    private void printAll(HttpServletRequest req) {
        req.getParameterNames().asIterator().forEachRemaining(paramName -> System.out.println(paramName + " : " + req.getParameter(paramName)));
    }
}
