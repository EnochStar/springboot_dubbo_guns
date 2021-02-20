package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.api.cinema.vo.FilmInfoVO;
import com.stylefeng.guns.api.cinema.vo.HallInfoVO;
import com.stylefeng.guns.api.film.vo.FilmInfo;
import com.stylefeng.guns.rest.common.persistence.model.MoocFieldT;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author EnochStar
 * @since 2021-02-19
 */
public interface MoocFieldTMapper extends BaseMapper<MoocFieldT> {
    List<FilmInfoVO> getFilmInfos(int cinemaId);
    HallInfoVO getHallInfo(int fieldId);
    FilmInfoVO getFilmInfoById(int field);
}