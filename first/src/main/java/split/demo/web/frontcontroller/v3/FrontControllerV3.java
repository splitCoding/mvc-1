package split.demo.web.frontcontroller.v3;

import split.demo.web.frontcontroller.ModelView;
import split.demo.web.frontcontroller.MyView;
import split.demo.web.frontcontroller.v2.ControllerV2;
import split.demo.web.frontcontroller.v2.MemberFormControllerV2;
import split.demo.web.frontcontroller.v2.MemberListControllerV2;
import split.demo.web.frontcontroller.v2.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();

    public FrontControllerV3() {
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerV3.service()");
        String requestURI = req.getRequestURI();

        ControllerV3 controllerV3 = controllerV3Map.get(requestURI);
        if (controllerV3 == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Map<String, String> paramMap = createParamMap(req);
        System.out.println(paramMap);
        ModelView mv = controllerV3.process(paramMap);

        String viewName = mv.getViewPath();
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), req, resp);
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap();
        req.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }

    private MyView viewResolver(String viewPath) {
        return new MyView("/WEB-INF/views/" + viewPath + ".jsp");
    }


}
