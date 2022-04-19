package com.simso.global.common.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class SingleResult<T> extends CommonResult {
    private T data;

}
