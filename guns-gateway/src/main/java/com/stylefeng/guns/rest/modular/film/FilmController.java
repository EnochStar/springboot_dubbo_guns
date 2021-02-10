package com.stylefeng.guns.rest.modular.film;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.film.FilmServiceApi;
import com.stylefeng.guns.rest.modular.film.vo.FilmIndexVO;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author EnochStar
 * @title: filmController
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/9 19:38
 */
@RestController
@RequestMapping("/film/")
public class FilmController {

    /*API网关：
        1、接口功能聚合
            好处：六个接口一次请求，减少Http请求
                同一个接口对外暴露，降低前后端分离开发的难度和复杂度
            坏处：
                一次性获取数据过度，容易出现问题
    */
    @Reference(interfaceClass = FilmServiceApi.class)
    private FilmServiceApi filmServiceApi;

    // 获取首页信息接口
    @GetMapping("getIndex")
    public ResponseVO getIndex() {
        FilmIndexVO filmIndexVO = new FilmIndexVO();
        // 获取banner信息
        filmServiceApi.getBanners();
        // 获取热映电影
        filmServiceApi.getHotFilms(true,8);
        // 即将上映的film
        filmServiceApi.getSoonFilms(true,8);
        // 票房排行榜
        filmServiceApi.getBoxRanking();
        // 人气榜
        filmServiceApi.getExpectRanking();
        // 前一百
        filmServiceApi.getTop();
        return ResponseVO.success(filmIndexVO);
    }

}
