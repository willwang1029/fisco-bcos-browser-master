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
        userMapper.createTbUser("user");
        int count = userMapper.getUser(userName, email);
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

    public BaseResponse verityUser(String userName, String passWord) {
        int count = userMapper.verityUser(userName, passWord);
        if(count != 1) { return new BaseResponse(ConstantCode.SYSTEM_ERROR); }
        else { return new BaseResponse(ConstantCode.SUCCESS); }
    }

    public BaseResponse getUserInfo(String userName){
        BaseResponse response=new BaseResponse(ConstantCode.SUCCESS);
        User userinfo=userMapper.getUserInfo(userName);
        response.setData(userinfo);
        return response;
    }

    public BaseResponse modifyUser(String userName, String passWord){
        BaseResponse response=new BaseResponse(ConstantCode.SUCCESS);
        userMapper.modifyUser(userName,passWord);
        return response;
    }
}
