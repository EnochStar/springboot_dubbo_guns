package com.stylefeng.guns.rest.modular.user.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.api.user.UserAPI;
import org.springframework.stereotype.Component;

/**
 * @author EnochStar
 * @title: UserImpl
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/4 23:00
 */
@Component
@Service(interfaceClass = UserAPI.class)
public class UserImpl implements UserAPI {
    @Override
    public boolean login(String username, String password) {
        System.out.println("this is user service!!" + username + ","+password);
        return false;
    }
}
