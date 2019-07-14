package org.bcos.browser.mapper;

import org.apache.ibatis.annotations.Param;
import org.bcos.browser.entity.dto.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    boolean add(User user);

    int getUser(@Param(value = "userName")String userName, @Param(value = "email")String email);

    int verityUser(@Param(value = "userName")String userName, @Param(value = "passWord")String passWord);

    User getUserInfo(@Param(value = "userName")String userName);

    void modifyUser(@Param(value = "userName")String userName,@Param(value = "passWord")String passWord);

    void createUser();
}
