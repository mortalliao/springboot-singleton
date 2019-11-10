package com.springboot.demo.auth.controller;

import com.springboot.demo.auth.utils.RequestResponseUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * controller基础抽象类
 *
 * @author Jim
 */
public abstract class BaseAction {

    /**
     * description 获得来的request中的 key value数据封装到map返回
     *
     * @param request 1
     * @return java.util.Map<java.lang.String       ,       java.lang.String>
     */
    @SuppressWarnings("rawtypes")
    protected Map<String, String> getRequestParameter(HttpServletRequest request) {
        return RequestResponseUtil.getRequestParameters(request);
    }

    protected Map<String, String> getRequestBody(HttpServletRequest request) {
        return RequestResponseUtil.getRequestBodyMap(request);
    }

    protected Map<String, String> getRequestHeader(HttpServletRequest request) {
        return RequestResponseUtil.getRequestHeaders(request);
    }
}
