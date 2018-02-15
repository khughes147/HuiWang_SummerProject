package com.example.QualificationAuthenticator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class NavController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/aboutUs", method = RequestMethod.GET)
    public String about() {
        return "about";
    }

    @RequestMapping(value = "/verification", method = RequestMethod.GET)
    public String verify() {
        return "verify";
    }


}
