package split.demo.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //응답코드
        resp.setStatus(HttpServletResponse.SC_OK);

        //응답헤더
        resp.setHeader("Content-Type", "text/plain;charset=utf-8");
        //편의 메서드
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        //쿠키
        resp.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        //편의 메서드
        Cookie cookie = new Cookie("myCookie2", "good2");
        cookie.setMaxAge(600);
        resp.addCookie(cookie);

        //redirect
        resp.setStatus(HttpServletResponse.SC_FOUND); //302
        resp.setHeader("Location", "/basic/hello-form.html");
        //편의 메서드
        resp.sendRedirect("/basic/hello-form.html");

        //messageBody
        PrintWriter writer = resp.getWriter();
        writer.write("success");
    }

}
