package split.demo.web.frontcontroller.v5;

import split.demo.web.frontcontroller.ModelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MyHandlerAdapter {
    boolean support(Object handler);

    ModelView handle(Object handler, HttpServletRequest req, HttpServletResponse resp);
}
