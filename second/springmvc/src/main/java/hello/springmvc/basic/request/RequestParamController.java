package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String age = req.getParameter("age");
        log.info("username={}, age={}", username, age);
        resp.getWriter().write("success");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) throws IOException {
        log.info("username={}, age={}", memberName, memberAge);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    //파라미터 이름이 변수의 이름과 같으면 생략 가능
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    //단순 타입일 때 생략 가능
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    //int에는 Null값이 들어갈 수 없기에 Integer 자료형 사용
    public String requestParamRequired(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "age", required = false) Integer age
    ) {
        log.info("required - username={}, age={}", username, age);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    //int에는 Null값이 들어갈 수 없기에 Integer 자료형 사용
    public String requestParamDefault(
            @RequestParam(value = "username", defaultValue = "guest") String username,
            @RequestParam(value = "age", defaultValue = "-1") int age
    ) {
        log.info("default - username={}, age={}", username, age);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap
    ) {
        Object username = paramMap.get("username");
        Object age = paramMap.get("age");
        log.info("paramMap - username={}, age={}", username, age);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String requestModelAttributeV1(@ModelAttribute HelloData data) {
        String username = data.getUsername();
        int age = data.getAge();
        log.info("requestModelAttributeV1 - username={}, age={}", username, age);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    //단순 타입이 아닌경우 생략시 @ModelAttribute 적용 ( argument resolver가 지정해준 타입 외 )
    public String requestModelAttributeV2(HelloData data) {
        String username = data.getUsername();
        int age = data.getAge();
        log.info("requestModelAttributeV2 - username={}, age={}", username, age);
        return "success";
    }


}
