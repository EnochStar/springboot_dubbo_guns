package com.stylefeng.guns.rest.modular.film;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.film.FilmServiceApi;
import com.stylefeng.guns.rest.modular.film.vo.FilmIndexVO;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    private static final String IMG_PRE = "http://img.meetingshop.cn/";

    // 获取首页信息接口
    @GetMapping("getIndex")
    public ResponseVO getIndex() {
        FilmIndexVO filmIndexVO = new FilmIndexVO();
        // 获取banner信息
        filmIndexVO.setBanners(filmServiceApi.getBanners());
        // 获取热映电影
        filmIndexVO.setHotFilms(filmServiceApi.getHotFilms(true,8));
        // 即将上映的film
        filmIndexVO.setSoonFilms(filmServiceApi.getSoonFilms(true,8));
        // 票房排行榜
        filmIndexVO.setBoxRanking(filmServiceApi.getBoxRanking());
        // 人气榜
        filmIndexVO.setExpectRanking(filmServiceApi.getExpectRanking());
        // 前一百
        filmIndexVO.setTop100(filmServiceApi.getTop());
        return ResponseVO.success(IMG_PRE,filmIndexVO);
    }

    @GetMapping("getConditionList")
    public ResponseVO getConditionList(@RequestParam(name = "cartId",required = false,defaultValue = "99") String catId,
                                       @RequestParam(name = "sourceId",required = false,defaultValue = "99") String sourceId,
                                       @RequestParam(name = "yearId",required = false,defaultValue = "99") String yearId) {
        // 类型集合

        // 片源集合

        // 年代集合
        return null;
    }

}
