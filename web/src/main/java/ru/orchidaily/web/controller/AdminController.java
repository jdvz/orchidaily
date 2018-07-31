package ru.orchidaily.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.orchidaily.web.data.ProductData;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/15/18 7:39 PM
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping("/")
    public String admin() {
        return "index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createProduct(Model model) {
        return "create.jsp";
    }
}
