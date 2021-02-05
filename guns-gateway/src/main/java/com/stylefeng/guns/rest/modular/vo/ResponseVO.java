package com.stylefeng.guns.rest.modular.vo;

/**
 * @author EnochStar
 * @title: ResponseVO
 * @projectName guns-parent
 * @description: TODO
 * @date 2021/2/5 21:19
 */
public class ResponseVO<M> {
    private Integer status;
    private String msg;
    // 返回数据实体
    private M data;

    public ResponseVO() {
    }

    public static <M> ResponseVO success(M m) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(m);
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
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public M getData() {
        return data;
    }

    public void setData(M data) {
        this.data = data;
    }
}
