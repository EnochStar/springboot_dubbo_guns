package com.stylefeng.guns.rest.modular.cinema.vo;

import com.stylefeng.guns.api.cinema.vo.AreaVO;
import com.stylefeng.guns.api.cinema.vo.BrandVO;
import com.stylefeng.guns.api.cinema.vo.HallInfoVO;
import com.stylefeng.guns.api.cinema.vo.HallTypeVO;
import lombok.Data;

import java.util.List;

/**
 * @author EnochStar
 * @title: CinemaConditionResponseVO
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/20 19:48
 */
@Data
public class CinemaConditionResponseVO {
    List<BrandVO> brands;
    List<HallTypeVO> hallTypeVOList;
    List<AreaVO> areas;
}
