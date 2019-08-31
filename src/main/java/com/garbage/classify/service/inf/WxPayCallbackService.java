package com.garbage.classify.service.inf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WxPayCallbackService {

    void payCallback(HttpServletRequest request, HttpServletResponse response);
}
