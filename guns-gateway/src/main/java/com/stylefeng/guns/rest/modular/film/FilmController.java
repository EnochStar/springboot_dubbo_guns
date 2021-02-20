package com.stylefeng.guns.rest.modular.film;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.stylefeng.guns.api.film.FilmAsyncServiceApi;
import com.stylefeng.guns.api.film.FilmServiceApi;
import com.stylefeng.guns.api.film.vo.*;
import com.stylefeng.guns.rest.modular.film.vo.FilmConditionVO;
import com.stylefeng.guns.rest.modular.film.vo.FilmIndexVO;
import com.stylefeng.guns.rest.modular.film.vo.FilmRequestVO;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
    @Reference(interfaceClass = FilmServiceApi.class,check = false)
    private FilmServiceApi filmServiceApi;

    @Reference(interfaceClass = FilmAsyncServiceApi.class,async = true,check = false)
    private FilmAsyncServiceApi filmAsyncServiceApi;

    private static final String IMG_PRE = "http://img.meetingshop.cn/";

    // 获取首页信息接口
    @GetMapping("getIndex")
    public ResponseVO getIndex() {
        FilmIndexVO filmIndexVO = new FilmIndexVO();
        // 获取banner信息
        filmIndexVO.setBanners(filmServiceApi.getBanners());
        // 获取热映电影
        filmIndexVO.setHotFilms(filmServiceApi.getHotFilms(true,8,1,99,99,99,99));
        // 即将上映的film
        filmIndexVO.setSoonFilms(filmServiceApi.getSoonFilms(true,8,1,99,99,99,99));
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
        FilmConditionVO filmConditionVO = new FilmConditionVO();
        // 类型集合
        List<CatVO> catVOS = filmServiceApi.getCats();
        // 标志位
        boolean flag = false;
        List<CatVO> catResult = new ArrayList<>();
        CatVO cat = null;
        for (CatVO catVO : catVOS) {
            // 判断集合是否存在catId，如果存在，则将相应实体变为active
            // 如果不存在 则默认将全部变为active状态
            if(catVO.getCatId().equals("99")){
                cat = catVO;
                continue;
            }
            if(catVO.getCatId().equals(catId)){
                flag = true;
                catVO.setActive(true);
            }else{
                catVO.setActive(false);
            }
            catResult.add(catVO);
        }
        // 如果不存在，则默认将全部变为Active状态
        if(!flag){
            cat.setActive(true);
            catResult.add(cat);
        }else{
            cat.setActive(false);
            catResult.add(cat);
        }
        // 片源集合
        flag=false;
        List<SourceVO> sources = filmServiceApi.getSources();
        List<SourceVO> sourceResult = new ArrayList<>();
        SourceVO sourceVO = null;
        for(SourceVO source : sources){
            if(source.getSourceId().equals("99")){
                sourceVO = source;
                continue;
            }
            if(source.getSourceId().equals(catId)){
                flag = true;
                source.setActive(true);
            }else{
                source.setActive(false);
            }
            sourceResult.add(source);
        }
        // 如果不存在，则默认将全部变为Active状态
        if(!flag){
            sourceVO.setActive(true);
            sourceResult.add(sourceVO);
        }else{
            sourceVO.setActive(false);
            sourceResult.add(sourceVO);
        }

        // 年代集合
        flag=false;
        List<YearVO> years = filmServiceApi.getYears();
        List<YearVO> yearResult = new ArrayList<>();
        YearVO yearVO = null;
        for(YearVO year : years){
            if(year.getYearId().equals("99")){
                yearVO = year;
                continue;
            }
            if(year.getYearId().equals(catId)){
                flag = true;
                year.setActive(true);
            }else{
                year.setActive(false);
            }
            yearResult.add(year);
        }
        // 如果不存在，则默认将全部变为Active状态
        if(!flag){
            yearVO.setActive(true);
            yearResult.add(yearVO);
        }else{
            yearVO.setActive(false);
            yearResult.add(yearVO);
        }

        filmConditionVO.setCatInfo(catResult);
        filmConditionVO.setSourceInfo(sourceResult);
        filmConditionVO.setYearInfo(yearResult);

        return ResponseVO.success(filmConditionVO);
    }

    @GetMapping("getFilms")
    public ResponseVO getFilms(FilmRequestVO filmRequestVO) {
        String img_pre = "http://img.meetingshop.cn/";

        FilmVO filmVO = null;
        // 根据showType判断影片查询类型
        switch (filmRequestVO.getShowType()){
            case 1 :
                filmVO = filmServiceApi.getHotFilms(
                        false,filmRequestVO.getPageSize(),filmRequestVO.getNowPage(),
                        filmRequestVO.getSortId(),filmRequestVO.getSourceId(),filmRequestVO.getYearId(),
                        filmRequestVO.getCatId());
                break;
            case 2 :
                filmVO = filmServiceApi.getSoonFilms(
                        false,filmRequestVO.getPageSize(),filmRequestVO.getNowPage(),
                        filmRequestVO.getSortId(),filmRequestVO.getSourceId(),filmRequestVO.getYearId(),
                        filmRequestVO.getCatId());
                break;
            case 3 :
                filmVO = filmServiceApi.getClassFilms(
                        filmRequestVO.getPageSize(),filmRequestVO.getNowPage(),
                        filmRequestVO.getSortId(),filmRequestVO.getSourceId(),
                        filmRequestVO.getYearId(), filmRequestVO.getCatId());
                break;
            default:
                filmVO = filmServiceApi.getHotFilms(
                        false,filmRequestVO.getPageSize(),filmRequestVO.getNowPage(),
                        filmRequestVO.getSortId(),filmRequestVO.getSourceId(),filmRequestVO.getYearId(),
                        filmRequestVO.getCatId());
                break;
        }
        // 根据sortId排序
        // 添加各种条件查询
        // 判断当前是第几页

        return ResponseVO.success(
                filmVO.getNowPage(),filmVO.getTotalPage(),
                img_pre,filmVO.getFilmInfos());
    }

    @GetMapping("films/{searchParam}")
    public ResponseVO films(@PathVariable("searchParam") String searchParam,
                            int searchType) throws ExecutionException, InterruptedException {
        // 根据searchType判断查询类型
        // 不同的查询类型，传入的条件有所不同
        FilmDetailVO filmDetail = filmServiceApi.getFilmDetail(searchType, searchParam);
        if (filmDetail == null){
            return ResponseVO.serviceFail("没有可查询的影片");
        }else if (filmDetail.getFilmId() == null || filmDetail.getFilmId().trim().length() == 0){
            return ResponseVO.serviceFail("没有可查询的影片");
        }
        String filmId = filmDetail.getFilmId();
        // 查询影片的详细信息 -》 Dubbo异步获取
        // 获取影片描述信息
        filmAsyncServiceApi.getFilmDesc(filmId);
        Future<FilmDescVO> filmDescVOFuture = RpcContext.getContext().getFuture();
        // 获取图片信息
        filmAsyncServiceApi.getImgs(filmId);
        Future<ImgVO> imgVOFuture = RpcContext.getContext().getFuture();
        // 获取导演信息
        filmAsyncServiceApi.getDectInfo(filmId);
        Future<ActorVO> actorVOFuture = RpcContext.getContext().getFuture();
        // 获取演员信息
        filmAsyncServiceApi.getActors(filmId);
        Future<List<ActorVO>> actorsVOFutrue = RpcContext.getContext().getFuture();

        // 组织info对象
        InfoRequestVO infoRequestVO = new InfoRequestVO();

        // 组织Actor属性
        ActorRequestVO actorRequestVO = new ActorRequestVO();
        actorRequestVO.setActors(actorsVOFutrue.get());
        actorRequestVO.setDirector(actorVOFuture.get());

        // 组织info对象
        infoRequestVO.setActors(actorRequestVO);
        infoRequestVO.setBiography(filmDescVOFuture.get().getBiography());
        infoRequestVO.setFilmId(filmId);
        infoRequestVO.setImgVO(imgVOFuture.get());

        // 组织成返回值
        filmDetail.setInfo04(infoRequestVO);
        return ResponseVO.success("http://img.meetingshop.cn/",filmDetail);
    }

}
