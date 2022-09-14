package com.abc.springboot;

import com.abc.springboot.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpringBootController {

    @Autowired
    private MyService myService;

    @RequestMapping(value = "/message")
    public @ResponseBody String say() {
        myService.sendMessageToSocket();
        return "hello boy!";
    }

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }
}