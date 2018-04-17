package example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试页
 *
 * @author yuhaiyang
 * @create 2018-04-17 10:51
 **/
@Controller
@RequestMapping("/home")
public class IndexController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
