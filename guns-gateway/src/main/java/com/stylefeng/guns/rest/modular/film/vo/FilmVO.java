package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;
import org.apache.tomcat.jni.FileInfo;

import java.util.List;

/**
 * @author EnochStar
 * @title: HotFilmVO
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/9 20:02
 */
@Data
public class FilmVO {
    private int filmNum;
    private List<FileInfo> filmInfos;
}
