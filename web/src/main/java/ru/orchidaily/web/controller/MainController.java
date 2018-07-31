package ru.orchidaily.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/15/18 12:41 PM
 */
@Controller
@RequestMapping("/")
public class MainController {
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "passed";
    }

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String indexjsp() {
        return "index2.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test2() {
        return "passed2";
    }
}
