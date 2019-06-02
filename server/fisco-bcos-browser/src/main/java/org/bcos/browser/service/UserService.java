package org.bcos.browser.service;

import lombok.extern.slf4j.Slf4j;
import org.bcos.browser.base.ConstantCode;
import org.bcos.browser.entity.base.BaseResponse;
import org.bcos.browser.entity.dto.User;
import org.bcos.browser.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public BaseResponse addUser(String userName, String email, String password) {
        System.out.println(userName);

        int count = userMapper.getUser(userName, email);
        System.out.println(count);
        if(count > 0) { return new BaseResponse(ConstantCode.SYSTEM_ERROR); }
        else {
            User user = new User();
            user.setUserName(userName);
            user.setEmail(email);
            user.setPassWord(password);
            userMapper.add(user);
            return new BaseResponse(ConstantCode.SUCCESS);
        }
    }
}
