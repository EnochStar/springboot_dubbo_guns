package com.stylefeng.guns.rest.modular.cinema.vo;

import com.stylefeng.guns.api.cinema.vo.CinemaVO;
import lombok.Data;

import java.util.List;

/**
 * @author EnochStar
 * @title: CinemaListResponseVO
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/20 19:42
 */
@Data
public class CinemaListResponseVO {
    private List<CinemaVO> cinemas;
}
