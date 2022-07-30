package split.demo.web.frontcontroller.v2;

import split.demo.web.frontcontroller.MyView;
import split.demo.web.frontcontroller.v1.ControllerV1;
import split.demo.web.frontcontroller.v1.MemberFormControllerV1;
import split.demo.web.frontcontroller.v1.MemberListControllerV1;
import split.demo.web.frontcontroller.v1.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerV2 extends HttpServlet {
    private Map<String, ControllerV2> controllerV2Map = new HashMap<>();

    public FrontControllerV2() {
        controllerV2Map.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerV2Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerV2Map.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerV2.service()");
        String requestURI = req.getRequestURI();

        ControllerV2 controllerV2 = controllerV2Map.get(requestURI);
        if (controllerV2 == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyView view = controllerV2.process(req, resp);
        view.render(req, resp);
    }
}
