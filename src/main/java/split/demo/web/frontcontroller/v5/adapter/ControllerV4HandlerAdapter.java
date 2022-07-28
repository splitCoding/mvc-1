package split.demo.web.frontcontroller.v5.adapter;

import split.demo.domain.member.Member;
import split.demo.web.frontcontroller.ModelView;
import split.demo.web.frontcontroller.v3.ControllerV3;
import split.demo.web.frontcontroller.v4.ControllerV4;
import split.demo.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean support(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(Object handler, HttpServletRequest req, HttpServletResponse resp) {
        ControllerV4 controllerV4 = (ControllerV4) handler;

        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(
                paramName -> paramMap.put(paramName, req.getParameter(paramName))
        );

        Map<String, Object> model = new HashMap<>();

        String view = controllerV4.process(paramMap, model);
        ModelView mv = new ModelView(view);
        mv.setModel(model);

        return mv;
    }
}
