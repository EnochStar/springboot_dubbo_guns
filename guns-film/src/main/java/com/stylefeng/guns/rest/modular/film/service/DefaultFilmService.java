package com.stylefeng.guns.rest.modular.film.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.api.film.FilmServiceApi;
import com.stylefeng.guns.api.film.vo.BannerVO;
import com.stylefeng.guns.api.film.vo.FilmInfo;
import com.stylefeng.guns.api.film.vo.FilmVO;
import com.stylefeng.guns.rest.common.persistence.dao.MoocBannerTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocBannerT;
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
        return null;
    }

    @Override
    public FilmVO getSoonFilms(boolean isLimit, int nums) {
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
