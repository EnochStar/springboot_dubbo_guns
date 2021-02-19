package com.stylefeng.guns.rest.modular.cinema;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.cinema.CinemaServiceAPI;
import com.stylefeng.guns.api.cinema.vo.CinemaQueryVO;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.web.bind.annotation.*;

/**
 * @author EnochStar
 * @title: CinemaController
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/19 14:51
 */
@RequestMapping("/cinema/")
@RestController
public class CinemaController {

    @Reference(interfaceClass = CinemaServiceAPI.class)
    private CinemaServiceAPI cinemaServiceAPI;

    @GetMapping("getCinemas")
    public ResponseVO getCinema(CinemaQueryVO cinemaQueryVO) {
        return null;
    }

    @GetMapping("getCondition")
    public ResponseVO getCondition(CinemaQueryVO cinemaQueryVO) {
        return null;
    }

    @GetMapping("getFields")
    public ResponseVO getFields(Integer cinemaId) {
        return null;
    }

    @PostMapping("getFieldInfo")
    public ResponseVO getFieldInfo(Integer cinemaId,Integer fieldId) {
        return null;
    }
}
