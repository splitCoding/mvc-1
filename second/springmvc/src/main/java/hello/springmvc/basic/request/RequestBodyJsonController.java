package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        HelloData body = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", body.getUsername(), body.getAge());
        resp.getWriter().write("success");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}", messageBody);
        HelloData body = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", body.getUsername(), body.getAge());
        return "success";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData body) throws IOException {
        log.info("username={}, age={}", body.getUsername(), body.getAge());
        return "success";
    }

    @PostMapping("/request-body-json-v4")
    public HttpEntity<String> requestBodyJsonV4(HttpEntity<HelloData> httpEntity) throws IOException {
        HelloData body = httpEntity.getBody();
        log.info("username={}, age={}", body.getUsername(), body.getAge());
        return new HttpEntity<>("success");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public String requestBodyJsonV5(@RequestBody HelloData body) throws IOException {
        log.info("username={}, age={}", body.getUsername(), body.getAge());
        return "success";
    }
}
