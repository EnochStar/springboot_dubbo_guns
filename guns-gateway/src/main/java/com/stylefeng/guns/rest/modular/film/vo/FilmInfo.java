package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

/**
 * @author EnochStar
 * @title: FilmInfo
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/9 20:02
 */
@Data
public class FilmInfo {
    private String filmId;
    private int filmType;
    private String imgAddress;
    private String filmName;
    private String filmScore;
    private int expectNum;
    private String showTime;
    private int boxNum;
    private String score;
}
