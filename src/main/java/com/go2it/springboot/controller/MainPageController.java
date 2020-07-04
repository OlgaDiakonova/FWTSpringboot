package com.go2it.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPageController {
    @RequestMapping("/")
    public String index() {

        return "<div style=\"text-align:center;\">"
                + "<h1>Fish Wholesale Trading Shop</h1>" +
                "<p> This is my first web-page </p>" +

                "<img src=https://thumbs.dreamstime.com/b/fish-store-new-york-market-chinatown-lower-manhattan-seafood-34334006.jpg></img>"

                + "</div>";

    }
}
