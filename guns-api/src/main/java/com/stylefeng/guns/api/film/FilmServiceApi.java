package com.stylefeng.guns.api.film;

import com.stylefeng.guns.api.film.vo.BannerVO;
import com.stylefeng.guns.api.film.vo.FilmInfo;
import com.stylefeng.guns.api.film.vo.FilmVO;

import java.util.List;

/**
 * @author EnochStar
 * @title: FilmServiceApi
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/9 20:36
 */
public interface FilmServiceApi {
    // 获得banners
    BannerVO getBanners();

    // 获取热映影片
    FilmVO getHotFilms(boolean isLimit,int num);

    // 获取即将上映的影片
    FilmVO getSoonFilms(boolean isLimit,int nums);

    // 获取票房排行榜
    List<FilmInfo> getBoxRanking();

    // 获取人气排行榜
    List<FilmInfo> getExpectRanking();

    // 获取top100
    List<FilmInfo> getTop();
}
