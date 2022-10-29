package com.backend.app.service.impl;

import com.backend.app.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @Author hym
 * @Date 2022/10/29
 * @Description demo
 */
public class BuyServiceImpl implements BuyService {

    @Autowired
    private RestTemplate restTemplate;

    private String backendUser = "backend-user";
}
