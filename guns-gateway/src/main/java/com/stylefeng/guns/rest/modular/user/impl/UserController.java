package com.stylefeng.guns.rest.modular.user.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.user.UserAPI;
import com.stylefeng.guns.api.user.UserModel;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author EnochStar
 * @title: UserController
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/6 20:25
 */
@RequestMapping("/user/")
@RestController
public class UserController {
    @Reference(interfaceClass = UserAPI.class)
    private UserAPI userAPI;

    @RequestMapping("register")
    public ResponseVO register(UserModel userModel) {
        if (userModel.getUsername() == null || userModel.getUsername().trim().length() == 0
                || userModel.getPassword() == null || userModel.getPassword().trim().length() == 0 ) {
            return ResponseVO.serviceFail("用户名或密码不能为空");
        }
        boolean isSuccess = userAPI.registry(userModel);
        if (isSuccess) {
            return ResponseVO.success("注册成功");
        }else{
            return ResponseVO.serviceFail("注册失败");
        }
    }

}
