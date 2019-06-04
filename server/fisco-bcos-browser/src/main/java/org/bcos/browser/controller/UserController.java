package org.bcos.browser.controller;

import org.bcos.browser.base.ConstantCode;
import org.bcos.browser.entity.base.BaseResponse;
import org.bcos.browser.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public BaseResponse addUser(@Valid @RequestBody String str) throws JSONException {
        JSONObject object = new JSONObject(str);
        return userService.addUser(object.getString("userName"), object.getString("email"), object.getString("passWord"));
    }

    @PostMapping("/verity")
    public BaseResponse verityUser(@Valid @RequestBody String str, HttpSession session) throws JSONException {
        JSONObject object = new JSONObject(str);
        session.setAttribute("userName", object.getString("userName"));
        return userService.verityUser(object.getString("userName"), object.getString("passWord"));
    }

    @GetMapping("/logOut")
    public BaseResponse logOut(HttpSession session) {
        if(session.getAttribute("userName") != null) {
            session.removeAttribute("userName");
            return new BaseResponse(ConstantCode.SUCCESS);
        } else { return new BaseResponse(ConstantCode.SYSTEM_ERROR); }
    }

}
