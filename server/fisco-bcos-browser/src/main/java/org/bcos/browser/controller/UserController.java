package org.bcos.browser.controller;

import org.bcos.browser.entity.base.BaseResponse;
import org.bcos.browser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public BaseResponse addUser(String userName, String email, String passWord) {
        return userService.addUser(userName, email, passWord);
    }

    @PostMapping("/verity")
    public BaseResponse verityUser(String userName, String passWord, HttpSession session) {
        session.setAttribute("userName", userName);
        return userService.verityUser(userName, passWord);
    }
}
