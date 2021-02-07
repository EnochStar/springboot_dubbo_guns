package com.stylefeng.guns.rest.modular.user.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.user.UserAPI;
import com.stylefeng.guns.api.user.UserInfoModel;
import com.stylefeng.guns.api.user.UserModel;
import com.stylefeng.guns.rest.common.CurrentUser;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.web.bind.annotation.*;

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
        boolean flag = userAPI.checkUsername(userModel.getUsername());
        if (!flag) return ResponseVO.serviceFail("用户名已存在");
        boolean isSuccess = userAPI.registry(userModel);
        if (isSuccess) {
            return ResponseVO.success("注册成功");
        }else{
            return ResponseVO.serviceFail("注册失败");
        }
    }
    @PostMapping("check")
    public ResponseVO check(String username) {
        if (username != null && username.trim().length() > 0) {
            if (userAPI.checkUsername(username)) {
                return ResponseVO.success("用户名不存在");
            }else{
                return ResponseVO.serviceFail("用户名已经存在");
            }
        }else{
            return ResponseVO.serviceFail("用户名不能为空");
        }
    }

    @GetMapping("logout")
    public ResponseVO logout() {
        /*
        涉及redis
        * 应用：
        * 1、前端存储JWT【7天】：JWT的刷新
        * 2、服务器端会存储活动用户信息
        * 3、JWT的userID为key，查找活跃用户
        *
        * 退出：
        * 1、前端删除JWT
        * 2、后端服务器删除活跃用户缓存
        *
        * 现状： 前端删除JWT即可
        */
        return ResponseVO.success("用户退出成功");
    }
    @GetMapping("getUserInfo")
    public ResponseVO getUserInfo() {
        // 获取当前登录用户
        // 将用户ID传入后端进行查询
        String userId = CurrentUser.getCurrentUser();
        if (userId != null && userId.trim().length() > 0) {
            int uuid = Integer.parseInt(userId);
            UserInfoModel userInfoModel = userAPI.getUserInfo(uuid);
            if (userInfoModel != null) {
                return ResponseVO.success(userInfoModel);
            }else{
                return ResponseVO.appFail("用户信息查询");
            }
        }else{
            return ResponseVO.serviceFail("用户未登录");
        }
    }

    @PostMapping("updateUserInfo")
    public ResponseVO updateUserInfo(UserInfoModel userInfoModel) {
        // 获取当前登录用户
        // 将用户ID传入后端进行查询
        String userId = CurrentUser.getCurrentUser();
        if (userId != null && userId.trim().length() > 0) {
            int uuid = Integer.parseInt(userId);
            if (uuid != userInfoModel.getUuid()) {
                return ResponseVO.serviceFail("请修改您的个人信息");
            }
            UserInfoModel userInfo = userAPI.updateUserInfo(userInfoModel);
            if (userInfo != null) {
                return ResponseVO.success(userInfoModel);
            }else{
                return ResponseVO.appFail("用户信息修改失败");
            }
        }else{
            return ResponseVO.serviceFail("用户未登录");
        }
    }
}
