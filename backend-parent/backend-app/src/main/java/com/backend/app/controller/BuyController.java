package com.backend.app.controller;

import com.backend.app.service.BuyService;
import com.backend.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author hym
 * @Date 2022/10/29
 * @Description demo
 */
@RestController
@RequestMapping("buy")
public class BuyController {
    @Autowired
    private BuyService buyService;
}
