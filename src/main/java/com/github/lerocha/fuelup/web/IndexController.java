package com.github.lerocha.fuelup.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String home(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        logger.info("index page request; name={}", name);
        return "index";
    }

    @RequestMapping("/login")
    public String home() {
        logger.info("login page request");
        return "login";
    }
}
