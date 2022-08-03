package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@Controller
public class ResponseBodyController {
    @GetMapping("/response-body-string-v1")
    public void responseBodyStringV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("spring1");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyStringV2() throws IOException {
        return new ResponseEntity("spring2", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyStringV3() {
        return "spring3";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData body = new HelloData();
        body.setUsername("kim1");
        body.setAge(23);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData body = new HelloData();
        body.setUsername("kim2");
        body.setAge(23);
        return body;
    }
}
