package com.stylefeng.guns.rest.modular.film.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.film.FilmServiceApi;
import com.stylefeng.guns.api.film.vo.BannerVO;
import com.stylefeng.guns.api.film.vo.FilmInfo;
import com.stylefeng.guns.api.film.vo.FilmVO;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.rest.common.persistence.dao.MoocBannerTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MoocFilmTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocBannerT;
import com.stylefeng.guns.rest.common.persistence.model.MoocFilmT;
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
        }else{

        }
        // 否则，则是列表页
        return null;
    }

    @Override
    public List<FilmInfo> getBoxRanking() {
        return null;
    }

    @Override
    public List<FilmInfo> getExpectRanking() {
        return null;
    }

    @Override
    public List<FilmInfo> getTop() {
        return null;
    }
}
