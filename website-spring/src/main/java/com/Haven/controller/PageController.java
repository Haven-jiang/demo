package com.Haven.controller;

import com.Haven.service.MenuService;
import com.Haven.vo.MenuVO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/pages")
@RestController
public class PageController {

    @Autowired
    MenuService menuService;

    @PostMapping("/menu")
    public MenuVO menu() {
        return menuService.menuBuild();
    }
}
