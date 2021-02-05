package com.stylefeng.guns.rest.common;

import com.stylefeng.guns.api.user.UserInfoModel;

/**
 * @author EnochStar
 * @title: CurrentUser
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/5 21:29
 */
public class CurrentUser {

    // 线程绑定的存储空间 将thread当作session使用

    private static final ThreadLocal<String> threadlocal = new ThreadLocal<>();

    // 将用户id放入存储空间
    public static void saveUserIdId(String userId) {
        threadlocal.set(userId);
    }
    public static String getCurrentUser() {
        return threadlocal.get();
    }
}
