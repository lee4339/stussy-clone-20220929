package com.stussy.stussyclone20220929.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShopPageController {

    @GetMapping("/collections/{category}")
    public String loadCollections(@PathVariable String category) {
        return "shop/collections";
    }

    @GetMapping("/products/{groupId}")
    public String loadProductDetail() {
        return "shop/product";
    }
}
