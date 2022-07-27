package split.demo.web.frontcontroller.v2;


import split.demo.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {
    MyView process(HttpServletRequest req, HttpServletResponse resq) throws ServletException, IOException;
}
