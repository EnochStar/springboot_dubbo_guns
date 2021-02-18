package com.stylefeng.guns.api.film.vo;

import com.stylefeng.guns.api.film.vo.ActorVO;
import lombok.Data;


import java.io.Serializable;
import java.util.List;

/**
 * @author EnochStar
 * @title: ActorRequestVO
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/18 16:46
 */
@Data
public class ActorRequestVO implements Serializable {
    private ActorVO director;
    private List<ActorVO> actors;
}
