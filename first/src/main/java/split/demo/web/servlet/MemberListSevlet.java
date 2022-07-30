package split.demo.web.servlet;

import split.demo.domain.member.Member;
import split.demo.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "memberListSevlet", urlPatterns = "/servlet/members")
public class MemberListSevlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter writer = resp.getWriter();
        writer.write("<html>" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Title</title>" +
                "</head>" +
                "<body>" +
                "<a href=\"/index.html\">메인</a>" +
                "<table>" +
                "    <thead>" +
                "    <th>id</th>" +
                "    <th>username</th>" +
                "    <th>age</th>" +
                "    </thead>" +
                "    <tbody>");
        for (Member member : members) {
            writer.write("    <tr>" +
                    "        <td>" + member.getId() + "</td>" +
                    "        <td>" + member.getUsername() + "</td>" +
                    "        <td>" + member.getAge() + "</td>" +
                    "    </tr>");
        }
        writer.write("    </tbody>" +
                "</table>" +
                "</body>" +
                "</html>");
    }
}
