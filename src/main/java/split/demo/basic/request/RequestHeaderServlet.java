package split.demo.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request_header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // http://localhost:8080/request_header?username=hi
        printStartLine(req);
        printHeaders(req);
        resp.getWriter().write("success");
    }

    private void printStartLine(HttpServletRequest req) {
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡreq lineㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        System.out.println("req.getMethod() : " + req.getMethod());
        System.out.println("req.getProtocol() : " + req.getProtocol());
        System.out.println("req.getScheme() : " + req.getScheme());
        System.out.println("req.getRequestURL() : " + req.getRequestURL());
        System.out.println("req.getRequestURI() : " + req.getRequestURI());
        System.out.println("req.getQueryString() : " + req.getQueryString());
        System.out.println("req.isSecure() : " + req.isSecure());
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
    }

    private void printHeaders(HttpServletRequest req) {
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡreq headerㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        req.getHeaderNames().asIterator().forEachRemaining(headerName -> System.out.println(headerName + " : " + req.getHeader(headerName)));
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
    }
}
