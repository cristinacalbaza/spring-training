package ro.msg.learning.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.dao.OrderDetailDAO;

@RestController
public class TestController {

    @RequestMapping("/")
    public String index(){

        return "Test successfully!";
    }
}
