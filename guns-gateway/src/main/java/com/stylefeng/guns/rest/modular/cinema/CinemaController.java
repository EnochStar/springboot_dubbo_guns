package com.stylefeng.guns.rest.modular.cinema;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.cinema.CinemaServiceAPI;
import com.stylefeng.guns.api.cinema.vo.*;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaConditionResponseVO;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaFieldResponseVO;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaFieldsResponseVO;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author EnochStar
 * @title: CinemaController
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/19 14:51
 */
@RequestMapping("/cinema/")
@RestController
@Slf4j
public class CinemaController {

    @Reference(interfaceClass = CinemaServiceAPI.class)
    private CinemaServiceAPI cinemaServiceAPI;

    private static final String IMG_PRE = "http://img.meetingshop.cn/";

    @GetMapping("getCinemas")
    public ResponseVO getCinema(CinemaQueryVO cinemaQueryVO) {
        try{
            Page<CinemaVO> cinemas = cinemaServiceAPI.getCinemas(cinemaQueryVO);
            if (cinemas.getRecords() == null || cinemas.getSize() == 0) {
                return ResponseVO.success("没有影院可查");
            }else{
                return ResponseVO.success(cinemas.getCurrent(), (int) cinemas.getPages(),"",cinemas.getRecords());
            }
        }catch (Exception e) {
            log.error("获取影院列表异常",e);
            return ResponseVO.serviceFail("查询影院列表失败");
        }
    }

    @GetMapping("getCondition")
    public ResponseVO getCondition(CinemaQueryVO cinemaQueryVO) {
        try{
            List<AreaVO> areas = cinemaServiceAPI.getAreas(cinemaQueryVO.getDistrictId());
            List<BrandVO> brands = cinemaServiceAPI.getBrands(cinemaQueryVO.getBrandId());
            List<HallTypeVO> hallTypes = cinemaServiceAPI.getHallTypes(cinemaQueryVO.getHallType());
            CinemaConditionResponseVO cinemaConditionResponseVO = new CinemaConditionResponseVO();
            cinemaConditionResponseVO.setAreas(areas);
            cinemaConditionResponseVO.setBrands(brands);
            cinemaConditionResponseVO.setHallTypeVOList(hallTypes);
            return ResponseVO.success(cinemaConditionResponseVO);
        }catch (Exception e) {
            log.error("获取条件列表失败",e);
            return ResponseVO.serviceFail("获取影院查询条件失败");
        }
    }

    @GetMapping("getFields")
    public ResponseVO getFields(Integer cinemaId) {
        try{
            CinemaInfoVO cinemaInfoVO = cinemaServiceAPI.getCinemaInfoById(cinemaId);

            List<FilmInfoVO> filmInfoVOList = cinemaServiceAPI.getFilmInfoByCinemaId(cinemaId);

            CinemaFieldsResponseVO cinemaFieldsResponseVO = new CinemaFieldsResponseVO();
            cinemaFieldsResponseVO.setCinemaInfo(cinemaInfoVO);
            cinemaFieldsResponseVO.setFilmList(filmInfoVOList);
            return ResponseVO.success(IMG_PRE,cinemaFieldsResponseVO);
        }catch (Exception e) {
            log.error("获取播放场次失败");
            return ResponseVO.serviceFail("获取播放场次失败");
        }
    }

    @PostMapping("getFieldInfo")
    public ResponseVO getFieldInfo(Integer cinemaId,Integer fieldId) {
        try{

            CinemaInfoVO cinemaInfoById = cinemaServiceAPI.getCinemaInfoById(cinemaId);
            FilmInfoVO filmInfoByFieldId = cinemaServiceAPI.getFilmInfoByFieldId(fieldId);
            HallInfoVO filmFieldInfo = cinemaServiceAPI.getFilmFieldInfo(fieldId);

            // 造几个销售的假数据，后续会对接订单接口
            filmFieldInfo.setSoldSeats("1,2,3");

            CinemaFieldResponseVO cinemaFieldResponseVO = new CinemaFieldResponseVO();
            cinemaFieldResponseVO.setCinemaInfo(cinemaInfoById);
            cinemaFieldResponseVO.setFilmInfo(filmInfoByFieldId);
            cinemaFieldResponseVO.setHallInfo(filmFieldInfo);

            return ResponseVO.success(IMG_PRE,cinemaFieldResponseVO);
        }catch (Exception e){
            log.error("获取选座信息失败",e);
            return ResponseVO.serviceFail("获取选座信息失败");
        }
    }
}
