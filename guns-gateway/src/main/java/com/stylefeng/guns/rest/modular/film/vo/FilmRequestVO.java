package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

/**
 * @author EnochStar
 * @title: FilmRequestVO
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/13 21:35
 */
@Data
public class FilmRequestVO {

    private Integer showType = 1;

    private Integer sortId = 1;

    private Integer sourceId = 99;

    private Integer catId = 99;

    private Integer yearId = 99;

    private Integer nowPage = 1;

    private Integer pageSize = 18;

}
