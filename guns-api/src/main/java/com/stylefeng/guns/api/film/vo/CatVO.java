package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author EnochStar
 * @title: CatVO
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/13 20:17
 */
@Data
public class CatVO implements Serializable {
    private String catId;
    private String catName;
    private boolean isActive;
}
