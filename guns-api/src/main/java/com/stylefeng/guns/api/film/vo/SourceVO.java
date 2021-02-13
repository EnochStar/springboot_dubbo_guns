package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author EnochStar
 * @title: SourceVO
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/13 20:18
 */
@Data
public class SourceVO implements Serializable {
    private String sourceId;
    private String sourceName;
    private boolean isActive;
}
