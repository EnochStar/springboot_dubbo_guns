package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author EnochStar
 * @title: HotFilmVO
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/9 20:02
 */
@Data
public class FilmVO implements Serializable {
    private int filmNum;
    private List<FilmInfo> filmInfos;
    private int nowPage;
    private int totalPage;
}
