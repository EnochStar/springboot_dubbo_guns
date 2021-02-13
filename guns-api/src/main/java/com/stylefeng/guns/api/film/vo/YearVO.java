package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author EnochStar
 * @title: YearVO
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/13 20:19
 */
@Data
public class YearVO implements Serializable {
    private String yearId;
    private String yearName;
    private boolean isActive;
}
