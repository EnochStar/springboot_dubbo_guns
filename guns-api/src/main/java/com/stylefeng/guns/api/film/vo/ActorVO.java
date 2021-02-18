package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author EnochStar
 * @title: ActorVO
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/18 12:00
 */
@Data
public class ActorVO implements Serializable {
    private String imgAddress;
    private String directorName;
    private String roleName;
}
