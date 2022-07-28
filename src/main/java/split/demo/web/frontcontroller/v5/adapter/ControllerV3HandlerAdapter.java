package split.demo.web.frontcontroller.v5.adapter;

import split.demo.web.frontcontroller.ModelView;
import split.demo.web.frontcontroller.v3.ControllerV3;
import split.demo.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean support(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(Object handler, HttpServletRequest req, HttpServletResponse resp) {
        ControllerV3 controllerV3 = (ControllerV3) handler;
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(
                paramName -> paramMap.put(paramName, req.getParameter(paramName))
        );
        ModelView mv = controllerV3.process(paramMap);
        return mv;
    }
}
