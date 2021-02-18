package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author EnochStar
 * @title: FilmDescVO
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/18 11:53
 */
@Data
public class FilmDescVO implements Serializable {
    private String biography;
    private String filmId;
}
