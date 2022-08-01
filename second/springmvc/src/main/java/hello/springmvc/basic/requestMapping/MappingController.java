package hello.springmvc.basic.requestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/hello-basic")
    public String requestMapping() {
        log.info("hello-basic");
        return "success";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mapping-get-v1");
        return "success";
    }

    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "success";
    }

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String userId) {
        log.info("mapping userId={}", userId);
        return userId;
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable("userId") String userId, @PathVariable("orderId") Long orderId) {
        log.info("mapping userId={}, orderId={}", userId, orderId);
        return userId + " , " + orderId;
    }

    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParams(@RequestParam("mode") String mode) {
        log.info("mapping-param");
        return mode;
    }

    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsume(HttpServletRequest req) {
        String contentType = req.getContentType();
        log.info("mapping-consume");
        return contentType;
    }

    @PostMapping(value = "/mapping-produce", consumes = "application/json")
    public String mappingProduce(HttpServletRequest req) {
        String contentType = req.getContentType();
        log.info("mapping-produce");
        return contentType;
    }

}
