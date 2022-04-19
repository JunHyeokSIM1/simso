package com.simso.global.common.response;

import com.simso.domain.Posts;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    public <T> SingleResult<T> getSingleResult(T data){
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }
    public <T> ListResult<T> getListResult(List<T> data){
        ListResult<T> result = new ListResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    public  CommonResult getSuccessResult() {
        CommonResult commonResult = new CommonResult();
        setSuccessResult(commonResult);
        return commonResult;
    }

    public  CommonResult getFailResult() {
        CommonResult commonResult = new CommonResult();
        setFailResult(commonResult);
        return commonResult;
    }

    private void setSuccessResult(CommonResult commonResult) {
        commonResult.setSuccess(true);
        commonResult.setCode(CommonResponse.SUCCESS.getCode());
        commonResult.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(CommonResult commonResult) {
        commonResult.setSuccess(true);
        commonResult.setCode(CommonResponse.FAIL.getCode());
        commonResult.setMsg(CommonResponse.FAIL.getMsg());
    }

}
