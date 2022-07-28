package split.demo.web.frontcontroller.v5;

import split.demo.web.frontcontroller.ModelView;
import split.demo.web.frontcontroller.MyView;
import split.demo.web.frontcontroller.v3.MemberFormControllerV3;
import split.demo.web.frontcontroller.v3.MemberListControllerV3;
import split.demo.web.frontcontroller.v3.MemberSaveControllerV3;
import split.demo.web.frontcontroller.v4.MemberFormControllerV4;
import split.demo.web.frontcontroller.v4.MemberListControllerV4;
import split.demo.web.frontcontroller.v4.MemberSaveControllerV4;
import split.demo.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import split.demo.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerV5 extends HttpServlet {
    private Map<String, Object> handlerMap = new HashMap<>();
    private List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerV5() {
        initHandlerMap();
        initHandlerAdapters();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object handler = getHandler(req);

        if (handler == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        ModelView mv = adapter.handle(handler, req, resp);

        MyView view = viewResolver(mv);
        view.render(mv.getModel(), req, resp);
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    private void initHandlerMap() {
        handlerMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        handlerMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.support(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler를 찾을 수 없습니다.");
    }

    private Object getHandler(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return handlerMap.get(requestURI);
    }

    private MyView viewResolver(ModelView mv) {
        String viewname = mv.getViewPath();
        return new MyView("/WEB-INF/views/" + viewname + ".jsp");
    }
}
