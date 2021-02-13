package com.stylefeng.guns.rest.modular.film.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.film.FilmServiceApi;
import com.stylefeng.guns.api.film.vo.*;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EnochStar
 * @title: DefaultFilmService
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/9 21:14
 */

@Component
@Service(interfaceClass = FilmServiceApi.class)
public class DefaultFilmService implements FilmServiceApi {

    @Autowired
    private MoocBannerTMapper moocBannerTMapper;

    @Autowired
    private MoocFilmTMapper moocFilmTMapper;

    @Autowired
    private MoocCatDictTMapper moocCatDictTMapper;

    @Autowired
    private MoocYearDictTMapper moocYearDictTMapper;

    @Autowired
    private MoocSourceDictTMapper moocSourceDictTMapper;

    @Override
    public List<BannerVO> getBanners() {
        List<MoocBannerT> moocBannerTS = moocBannerTMapper.selectList(null);
        List<BannerVO> bannerVOList = new ArrayList<>();
        for (MoocBannerT moocBannerT : moocBannerTS) {
            BannerVO bannerVO = new BannerVO();

            bannerVO.setBannerId(moocBannerT.getUuid() + "");
            bannerVO.setBannerUrl(moocBannerT.getBannerUrl());
            bannerVO.setBannerAddress(moocBannerT.getBannerAddress());
            bannerVOList.add(bannerVO);
        }
        return bannerVOList;
    }

    @Override
    public FilmVO getHotFilms(boolean isLimit, int num) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfo> filmInfos = new ArrayList<>();
        // 判断是否为首页需要的条数
        // 如果是，则限制条数
        EntityWrapper<MoocFilmT> filmTEntityWrapper = new EntityWrapper<>();
        // 说明是热映影片
        filmTEntityWrapper.eq("film_status",1);
        if (isLimit) {
            Page<MoocFilmT> page = new Page<>(1,num);
            List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page, filmTEntityWrapper);
            // 组织filmInfos
            filmInfos = getFilmInfos(moocFilmTS);
            filmVO.setFilmNum(moocFilmTS.size());
            filmVO.setFilmInfos(filmInfos);
        }else{

        }
        // 否则，则是列表页
        return null;
    }

    private List<FilmInfo> getFilmInfos(List<MoocFilmT> moocFilmTS) {
        List<FilmInfo> filmInfoList = new ArrayList<>();
        for (MoocFilmT filmT : moocFilmTS) {
            FilmInfo filmInfo = new FilmInfo();
            filmInfo.setScore(filmT.getFilmScore());
            filmInfo.setImgAddress(filmT.getImgAddress());
            filmInfo.setFilmType(filmT.getFilmType());
            filmInfo.setFilmScore(filmT.getFilmScore());
            filmInfo.setFilmName(filmT.getFilmName());
            filmInfo.setFilmId(filmT.getUuid()+"");
            filmInfo.setExpectNum(filmT.getFilmPresalenum());
            filmInfo.setBoxNum(filmT.getFilmBoxOffice());
            filmInfo.setShowTime(DateUtil.getDay(filmT.getFilmTime()));

            // 将转换的对象放入结果集
            filmInfoList.add(filmInfo);
        }
        return filmInfoList;
    }

    @Override
    public FilmVO getSoonFilms(boolean isLimit, int nums) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfo> filmInfos = new ArrayList<>();
        // 判断是否为首页需要的条数
        // 如果是，则限制条数
        EntityWrapper<MoocFilmT> filmTEntityWrapper = new EntityWrapper<>();
        // 说明是热映影片
        filmTEntityWrapper.eq("film_status",1);
        if (isLimit) {
            Page<MoocFilmT> page = new Page<>(2,nums);
            List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page, filmTEntityWrapper);
            // 组织filmInfos
            filmInfos = getFilmInfos(moocFilmTS);
            filmVO.setFilmNum(moocFilmTS.size());
            filmVO.setFilmInfos(filmInfos);
        }else{

        }
        // 否则，则是列表页
        return null;
    }

    @Override
    public List<FilmInfo> getBoxRanking() {
        // 条件 -》 正在上映的，票房前十名
        EntityWrapper<MoocFilmT> filmTEntityWrapper = new EntityWrapper<>();
        // 说明是热映影片
        filmTEntityWrapper.eq("film_status",1);

        Page<MoocFilmT> page = new Page<>(1,10,"film_box_office");

        List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page,filmTEntityWrapper);
        List<FilmInfo> filmInfos = getFilmInfos(moocFilmTS);
        return filmInfos;
    }

    @Override
    public List<FilmInfo> getExpectRanking() {
        // 即将上映的 预售前十名
        EntityWrapper<MoocFilmT> filmTEntityWrapper = new EntityWrapper<>();
        // 说明是热映影片
        filmTEntityWrapper.eq("film_status",2);

        Page<MoocFilmT> page = new Page<>(1,10,"film_preSaleNum");

        List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page,filmTEntityWrapper);
        List<FilmInfo> filmInfos = getFilmInfos(moocFilmTS);
        return filmInfos;
    }

    @Override
    public List<FilmInfo> getTop() {
        // 正在上映的 评分前十
        EntityWrapper<MoocFilmT> filmTEntityWrapper = new EntityWrapper<>();
        // 说明是热映影片
        filmTEntityWrapper.eq("film_status",1);

        Page<MoocFilmT> page = new Page<>(1,10,"film_score");

        List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page,filmTEntityWrapper);
        List<FilmInfo> filmInfos = getFilmInfos(moocFilmTS);
        return filmInfos;
    }

    @Override
    public List<CatVO> getCats() {
        // 查询实体对象
        List<MoocCatDictT> moocCatDictTS = moocCatDictTMapper.selectList(null);
        List<CatVO> catVOS = new ArrayList<>();
        // 将实体对象转化为业务对象
        for (MoocCatDictT moocCatDictT : moocCatDictTS) {
            CatVO catVO = new CatVO();
            catVO.setCatId(moocCatDictT.getUuid() + "");
            catVO.setCatName(moocCatDictT.getShowName());

            catVOS.add(catVO);
        }

        return catVOS;
    }

    @Override
    public List<SourceVO> getSources() {
        List<SourceVO> sourceVOS = new ArrayList<>();
        List<MoocSourceDictT> sourceDictTS = moocSourceDictTMapper.selectList(null);
        for (MoocSourceDictT moocSourceDictT : sourceDictTS){
            SourceVO sourceVO = new SourceVO();
            sourceVO.setSourceId(moocSourceDictT.getUuid() + "");
            sourceVO.setSourceName(moocSourceDictT.getShowName());

            sourceVOS.add(sourceVO);
        }

        return sourceVOS;
    }

    @Override
    public List<YearVO> getYears() {
        // 查询实体对象
        List<MoocYearDictT> moocYearDictTS = moocYearDictTMapper.selectList(null);
        List<YearVO> yearVOS = new ArrayList<>();
        // 将实体对象转化为业务对象
        for (MoocYearDictT moocYearDictT : moocYearDictTS) {
            YearVO yearVO = new YearVO();
            yearVO.setYearId(moocYearDictT.getUuid() + "");
            yearVO.setYearName(moocYearDictT.getShowName());
            yearVOS.add(yearVO);
        }

        return yearVOS;
    }
}
