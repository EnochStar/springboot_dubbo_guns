package com.stylefeng.guns.rest.modular.user.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.api.user.UserAPI;
import com.stylefeng.guns.api.user.UserInfoModel;
import com.stylefeng.guns.api.user.UserModel;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.common.persistence.dao.MoocUserTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocUserT;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    @Autowired
    private MoocUserTMapper moocUserTMapper;
    @Override
    public int login(String username, String password) {
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUserName(username);
        MoocUserT result = moocUserTMapper.selectOne(moocUserT);
        if (result != null && result.getUuid() > 0) {
            String md5Password = MD5Util.encrypt(password);
            if (md5Password.equals(result.getUserPwd())) {
                return result.getUuid();
            }
        }
        return 0;
    }

    @Override
    public boolean registry(UserModel userModel) {
        // 将注册信息实体转化为数据实体
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUserName(userModel.getUsername());
        // 一般密码不做明文传输 因为不安全，故会做数据加密
        // moocUserT.setUserPwd(userModel.getPassword());
        moocUserT.setEmail(userModel.getEmail());
        moocUserT.setAddress(userModel.getAddress());
        moocUserT.setUserPhone(userModel.getPhone());

        //数据加密【MD5混淆加密 + 盐值  -->  Shiro加密】
        // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // String encode = passwordEncoder.encode(password);


        // 简单md5 加密
        String md5Password = MD5Util.encrypt(userModel.getPassword());
        moocUserT.setUserPwd(md5Password);

        // 将数据实体取入数据库
        Integer insert = moocUserTMapper.insert(moocUserT);
        if (insert > 0) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean checkUsername(String username) {
        EntityWrapper<MoocUserT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("user_name",username);
        Integer result = moocUserTMapper.selectCount(entityWrapper);
        if (result != null && result > 0) {
            return false;
        }else{
            return true;
        }
    }

    @Override
    public UserInfoModel getUserInfo(int uuid) {
        MoocUserT moocUserT = moocUserTMapper.selectById(uuid);
        return convertFromUserDOToUserVO(moocUserT);
    }

    @Override
    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {
        // 将传入的参数转换为DO 【MoocUserT】
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUuid(userInfoModel.getUuid());
        moocUserT.setNickName(userInfoModel.getNickname());
        moocUserT.setLifeState(Integer.parseInt(userInfoModel.getLifeState()));
        moocUserT.setBirthday(userInfoModel.getBirthday());
        System.out.println("=====>"+userInfoModel.getBiography());
        moocUserT.setBiography(userInfoModel.getBiography());
        moocUserT.setBeginTime(null);
        moocUserT.setHeadUrl(userInfoModel.getHeadAddress());
        moocUserT.setEmail(userInfoModel.getEmail());
        moocUserT.setAddress(userInfoModel.getAddress());
        moocUserT.setUserPhone(userInfoModel.getPhone());
        moocUserT.setUserSex(userInfoModel.getSex());
        moocUserT.setUpdateTime(null);

        // DO存入数据库
        Integer integer = moocUserTMapper.updateById(moocUserT);
        if(integer>0){
            // 将数据从数据库中读取出来
            UserInfoModel userInfo = getUserInfo(moocUserT.getUuid());
            // 将结果返回给前端
            return userInfo;
        }else{
            return null;
        }
    }

    public UserInfoModel convertFromUserDOToUserVO (MoocUserT moocUserT) {
        UserInfoModel userInfoModel = new UserInfoModel();

        userInfoModel.setUuid(moocUserT.getUuid());
        userInfoModel.setHeadAddress(moocUserT.getHeadUrl());
        userInfoModel.setPhone(moocUserT.getUserPhone());
        userInfoModel.setUpdateTime(moocUserT.getUpdateTime().getTime());
        userInfoModel.setEmail(moocUserT.getEmail());
        userInfoModel.setUsername(moocUserT.getUserName());
        userInfoModel.setNickname(moocUserT.getNickName());
        userInfoModel.setLifeState(""+moocUserT.getLifeState());
        userInfoModel.setBirthday(moocUserT.getBirthday());
        userInfoModel.setAddress(moocUserT.getAddress());
        userInfoModel.setSex(moocUserT.getUserSex());
        userInfoModel.setBeginTime(moocUserT.getBeginTime().getTime());
        userInfoModel.setBiography(moocUserT.getBiography());

        return userInfoModel;
    }

    private Date time2Date(long time) {
        Date date = new Date(time);
        return date;
    }
}
