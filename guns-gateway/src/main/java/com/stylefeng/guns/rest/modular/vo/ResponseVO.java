package com.stylefeng.guns.rest.modular.vo;

import lombok.Data;

/**
 * @author EnochStar
 * @title: ResponseVO
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/5 21:19
 */
@Data
public class ResponseVO<M> {
    private Integer status;
    private String msg;
    // 返回数据实体
    private M data;

    private String imgPre;

    public ResponseVO() {
    }

    public static <M> ResponseVO success(String imgPre,M m) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(m);
        responseVO.setImgPre(imgPre);
        return responseVO;
    }

    public static <M> ResponseVO success(M m) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(m);
        return responseVO;
    }
    public static <M> ResponseVO success(String message) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setMsg(message);
        return responseVO;
    }
    public static <M> ResponseVO serviceFail(String msg) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(1);
        responseVO.setMsg(msg);
        return responseVO;
    }
    public static <M> ResponseVO appFail(String msg) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(999);
        responseVO.setMsg(msg);
        return responseVO;
    }
}
