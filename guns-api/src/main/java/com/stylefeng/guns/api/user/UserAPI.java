package com.stylefeng.guns.api.user;

import com.stylefeng.guns.api.user.vo.UserInfoModel;
import com.stylefeng.guns.api.user.vo.UserModel;

/**
 * @author EnochStar
 * @title: UserAPI
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/4 23:00
 */
public interface UserAPI {
    int login(String username,String password);

    boolean registry(UserModel userModel);

    boolean checkUsername(String username);

    UserInfoModel getUserInfo(int uuid);

    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);

}
