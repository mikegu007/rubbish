package com.garbage.classify.service.inf;

import javax.servlet.http.HttpServletRequest;

public interface CreateWxOrderService {

    String createUnifiedOrder(HttpServletRequest request, String amount, String openid, String orderNo);
}
